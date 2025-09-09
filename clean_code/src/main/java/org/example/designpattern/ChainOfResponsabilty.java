import java.util.Optional;

interface Handler {
  Optional<String> handle(String req);
  void setNext(Handler next);
}

abstract class BaseHandler implements Handler {
  private Handler next;
  public void setNext(Handler next){ this.next = next; }
  public Optional<String> pass(String req){
    return (next == null) ? Optional.empty() : next.handle(req);
  }
}

class AuthHandler extends BaseHandler {
  public Optional<String> handle(String req){
    if (!req.startsWith("Bearer ")) return Optional.of("401 Unauthorized");
    return pass(req);
  }
}

class ValidationHandler extends BaseHandler {
  public Optional<String> handle(String req){
    if (req.length() < 15) return Optional.of("400 Bad Request");
    return pass(req);
  }
}

class BusinessHandler extends BaseHandler {
  public Optional<String> handle(String req){
    return Optional.of("200 OK processed " + req);
  }
}
