package app.controller;

import app.cache.AppCache;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @DeleteMapping("/clear")
    public String clear() {
        AppCache.getInstance().clear();
        return "Cache cleared";
    }
}
