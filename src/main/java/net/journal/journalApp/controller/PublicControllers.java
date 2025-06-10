package net.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import net.journal.journalApp.cache.AppCache;
import net.journal.journalApp.constants.PlaceHolder;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.responses.WeatherResponse;
import net.journal.journalApp.services.UserService;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicControllers {
    
    private static final Dotenv dotenv = Dotenv.load();
    private static final String openWeatherApiKey = dotenv.get("OPENWEATHER_API_KEY");
    
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;    

    @Autowired
    private AppCache appCache;

   @GetMapping("/health")
   public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("Alive");
   } 

   @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity userEntity){
        UserEntity created = userService.createUser(userEntity);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeather(){
        final String API = appCache.getAppCache().get(AppCache.keys.WEATHER_API.name());
        String finalApi = API.replace(PlaceHolder.CITY, "London").replace(PlaceHolder.API_KEY, openWeatherApiKey);
        log.info(finalApi);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        log.info("Weather Response: {}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

}
