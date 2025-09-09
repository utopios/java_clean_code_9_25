interface Button { String render(); }
interface Checkbox { String render(); }

class LightButton implements Button { public String render(){ return "[LightButton]"; } }
class DarkButton  implements Button { public String render(){ return "[DarkButton]";  } }

class LightCheckbox implements Checkbox { public String render(){ return "( ) Light"; } }
class DarkCheckbox  implements Checkbox  { public String render(){ return "( ) Dark";  }

interface UIFactory {
  Button createButton();
  Checkbox createCheckbox();
}

class LightFactory implements UIFactory {
  public Button createButton(){ return new LightButton(); }
  public Checkbox createCheckbox(){ return new LightCheckbox(); }
}
class DarkFactory implements UIFactory {
  public Button createButton(){ return new DarkButton(); }
  public Checkbox createCheckbox(){ return new DarkCheckbox(); }
}

class Screen {
  private final Button btn;
  private final Checkbox chk;
  Screen(UIFactory f){ this.btn = f.createButton(); this.chk = f.createCheckbox(); }
  public String draw(){ return btn.render() + " " + chk.render(); }
}


public class DemoFactory {
  public static void main(String[] args) {
    UIFactory factory = new LightFactory();
    Screen screen = new Screen(factory);
    System.out.println(screen.draw());

    factory = new DarkFactory();
    screen = new Screen(factory);
    System.out.println(screen.draw());
  }
}