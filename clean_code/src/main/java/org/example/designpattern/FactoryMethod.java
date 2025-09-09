interface Transport { String deliver(String payload); }

class Truck implements Transport {
  public String deliver(String payload) { return "Road: " + payload; }
}
class Ship implements Transport {
  public String deliver(String payload) { return "Sea: " + payload; }
}

abstract class Logistics {
  public String planDelivery(String payload) {
    Transport t = createTransport();
    return t.deliver(payload);
  }
    protected abstract Transport createTransport();
}

class RoadLogistics extends Logistics {
  protected Transport createTransport() { return new Truck(); }
}

class SeaLogistics extends Logistics {
  protected Transport createTransport() { return new Ship(); }
}

public class FactoryMethod {
  public static void main(String[] args) {
    Logistics logistics = new RoadLogistics();
    System.out.println(logistics.planDelivery("Package 1"));

    logistics = new SeaLogistics();
    System.out.println(logistics.planDelivery("Package 2"));
  }
}