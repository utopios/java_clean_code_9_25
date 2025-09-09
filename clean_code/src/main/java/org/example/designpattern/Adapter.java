interface Payment {
  void payCents(int cents);
}

class LegacyPaymentService {
  public void processEuros(double euros) {
    System.out.println("Legacy processed " + euros + "€");
  }
}

class LegacyPaymentAdapter implements Payment {
  private final LegacyPaymentService legacy;
  public LegacyPaymentAdapter(LegacyPaymentService legacy) { this.legacy = legacy; }

  @Override public void payCents(int cents) {
    double euros = cents / 100.0;
    legacy.processEuros(euros);
  }
}

public class Adapter {
  public static void main(String[] args) {
    Payment payment = new LegacyPaymentAdapter(new LegacyPaymentService());
    payment.payCents(1234);
  }
}