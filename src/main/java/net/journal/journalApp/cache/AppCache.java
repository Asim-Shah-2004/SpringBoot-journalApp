package net.journal.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import net.journal.journalApp.entity.CacheEntity;
import net.journal.journalApp.repository.CacheRepository;

@Data
@Component
public class AppCache {
    
    public enum keys{
        WEATHER_API
    }

    private Map<String,String> AppCache;

    @Autowired
    private CacheRepository cacheRepository;

    @PostConstruct
    public void init(){
        AppCache = new HashMap<>();
        List<CacheEntity> all = cacheRepository.findAll();
        for(CacheEntity entity : all){
            AppCache.put(entity.getKey(), entity.getValue());
        }
    }
}
