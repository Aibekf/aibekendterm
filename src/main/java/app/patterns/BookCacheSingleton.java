package app.patterns;

import app.dto.response.BookResponse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BookCacheSingleton {

    private static BookCacheSingleton instance;
    private final Map<String, List<BookResponse>> cache = new ConcurrentHashMap<>();

    private BookCacheSingleton() {}

    public static synchronized BookCacheSingleton getInstance() {
        if (instance == null) {
            instance = new BookCacheSingleton();
        }
        return instance;
    }

    public List<BookResponse> get(String key) {
        return cache.get(key);
    }

    public void put(String key, List<BookResponse> value) {
        cache.put(key, value);
    }

    public void clear() {
        cache.clear();
    }
}
