interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
        System.out.println("Connexion à MySQL...");
    }
}

class PostgreSQLDatabase implements Database {
    public void connect() {
        System.out.println("Connexion à PostgreSQL...");
    }
}

class UserRepository {
    private Database db;

    public UserRepository(Database db) {
        this.db = db;
    }

    public void saveUser(String user) {
        db.connect();
        System.out.println("Sauvegarde de " + user);
    }
}
