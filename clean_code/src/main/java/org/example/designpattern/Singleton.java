public final class AppConfig {
  private final String baseUrl;
  private final int timeoutMs;

  private AppConfig() {
    this.baseUrl = System.getProperty("app.baseUrl", "https://api.example.com");
    this.timeoutMs = Integer.getInteger("app.timeoutMs", 5000);
  }
  private static class Holder { private static final AppConfig INSTANCE = new AppConfig(); }
  public static AppConfig getInstance() { return Holder.INSTANCE; }

  public String baseUrl() { return baseUrl; }
  public int timeoutMs() { return timeoutMs; }

  // Exemple d'usage
  public static void main(String[] args) {
    AppConfig cfg = AppConfig.getInstance();
    System.out.println(cfg.baseUrl() + " / " + cfg.timeoutMs());
  }
}
