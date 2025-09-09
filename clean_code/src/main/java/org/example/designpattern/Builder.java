public final class Order {
  private final String id;
  private final String customerId;
  private final List<String> items;
  private final boolean express;

  private Order(Builder b) {
    this.id = Objects.requireNonNull(b.id);
    this.customerId = Objects.requireNonNull(b.customerId);
    this.items = List.copyOf(b.items);
    this.express = b.express;
  }

  public static class Builder {
    private String id;
    private String customerId;
    private List<String> items = new ArrayList<>();
    private boolean express;

    public Builder id(String v){ this.id = v; return this; }
    public Builder customerId(String v){ this.customerId = v; return this; }
    public Builder addItem(String v){ this.items.add(v); return this; }
    public Builder express(boolean v){ this.express = v; return this; }
    public Order build(){ return new Order(this); }
  }
}

public class DemoBuilder {
  public static void main(String[] args) {
    Order order = new Order.Builder()
        .id("order-1")
        .customerId("cust-1")
        .addItem("item-1")
        .addItem("item-2")
        .express(true)
        .build();
    System.out.println(order);
  }
}