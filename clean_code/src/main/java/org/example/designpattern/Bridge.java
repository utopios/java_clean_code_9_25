interface Renderer {
  void drawCircle(int x, int y, int radius);
}

class VectorRenderer implements Renderer {
  public void drawCircle(int x, int y, int radius) {
    System.out.println("Draw vector circle @(" + x + "," + y + "), r=" + radius);
  }
}

class RasterRenderer implements Renderer {
  public void drawCircle(int x, int y, int radius) {
    System.out.println("Draw raster circle @(" + x + "," + y + "), r=" + radius);
  }
}


abstract class Shape {
  protected final Renderer renderer;
  protected Shape(Renderer renderer) { this.renderer = renderer; }
  public abstract void draw();
}

class Circle extends Shape {
  private final int x, y, r;
  public Circle(Renderer renderer, int x, int y, int r) {
    super(renderer); this.x = x; this.y = y; this.r = r;
  }
  public void draw() { renderer.drawCircle(x, y, r); }
}

public class Bridge {
  public static void main(String[] args) {
    Renderer raster = new RasterRenderer();
    Renderer vector = new VectorRenderer();

    Shape circle = new Circle(raster, 5, 10, 20);
    circle.draw();

    circle = new Circle(vector, 5, 10, 20);
    circle.draw();
  }
}