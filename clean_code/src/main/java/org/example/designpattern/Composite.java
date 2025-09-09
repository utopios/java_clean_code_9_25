interface Node {
  String name();
  int size();
  void print(String indent);
}

class FileNode implements Node {
  private final String name; private final int bytes;
  public FileNode(String name, int bytes) { this.name = name; this.bytes = bytes; }
  public String name(){ return name; }
  public int size(){ return bytes; }
  public void print(String indent){ System.out.println(indent + "- " + name + " (" + bytes + "B)"); }
}


class FolderNode implements Node {
  private final String name;
  private final List<Node> children = new ArrayList<>();
  public FolderNode(String name){ this.name = name; }
  public String name(){ return name; }
  public int size(){ return children.stream().mapToInt(Node::size).sum(); }
  public void add(Node n){ children.add(n); }
  public void remove(Node n){ children.remove(n); }
  public void print(String indent){
    System.out.println(indent + "+ " + name + " [" + size() + "B]");
    for (Node c : children) c.print(indent + "  ");
  }
}