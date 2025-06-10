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

import lombok.extern.slf4j.Slf4j;
import net.journal.journalApp.entity.UserEntity;
import net.journal.journalApp.responses.WeatherResponse;
import net.journal.journalApp.services.UserService;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicControllers {
    
    private static final String API = "";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;    

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
        log.info("Fetching weather data from OpenWeather API");
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(API, HttpMethod.GET, null, WeatherResponse.class);
        log.info("Weather Response: {}", response.getBody());
        return ResponseEntity.ok(response.getBody());
    }

}
