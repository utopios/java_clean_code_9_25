package org.example.peche;

public class UserController {

    Response createUser(HttpRequest req) {
        String email = req.getParam("email");
        if (!email.matches(".+@.+")) return bad("bad email");
        var user = new User(email);
        Db.save(user);
        return ok(user);
    }
}
