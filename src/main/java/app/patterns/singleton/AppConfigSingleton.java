package app.patterns.singleton;


public class AppConfigSingleton {

    private static AppConfigSingleton instance;

    private AppConfigSingleton() {}

    public static synchronized AppConfigSingleton getInstance() {
        if (instance == null) instance = new AppConfigSingleton();
        return instance;
    }

    public String appName() {
        return "Endterm Library API";
    }
}
