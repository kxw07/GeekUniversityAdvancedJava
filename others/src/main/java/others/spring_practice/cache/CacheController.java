package others.spring_practice.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("/find/{id}")
    public String find(@PathVariable String id) {
        return cacheService.find(id);
    }

    @GetMapping("/evict/{id}")
    public String evict(@PathVariable String id) {
        return cacheService.evict(id);
    }

    @GetMapping("/put/{id}")
    public String put(@PathVariable String id) {
        return cacheService.put(id);
    }
}
