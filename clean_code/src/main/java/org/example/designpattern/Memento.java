class TextEditor {
  private String text = "";

    public void write(String newText) { text += newText; }
    public String getText() { return text; }
    public Memento save() { return new Memento(text); }
    public void restore(Memento m) { text = m.text; }
    public static class Memento {
        private final String text;
        private Memento(String text){ this.text = text; }
    }
}