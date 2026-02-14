package app.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class AppCache {

    private static volatile AppCache instance;
    private final Map<String, Object> store = new ConcurrentHashMap<>();

    private AppCache() {}

    public static AppCache getInstance() {
        if (instance == null) {
            synchronized (AppCache.class) {
                if (instance == null) instance = new AppCache();
            }
        }
        return instance;
    }

    public <T> void put(String key, T value) {
        store.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) store.get(key);
    }

    public void remove(String key) {
        store.remove(key);
    }

    public void clear() {
        store.clear();
    }
}

