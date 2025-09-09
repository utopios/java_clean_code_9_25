// class Bird {
//     void fly() {
//         System.out.println("Flying");
//     }
// }

// class Ostrich extends Bird {
//     @Override
//     void fly() {
//         throw new UnsupportedOperationException("Ostriches can't fly");
//     }
// }

interface Bird {
    void move();
}

class Sparrow implements Bird {
    @Override
    public void move() {
        System.out.println("Flying");
    }
}
class Ostrich implements Bird {
    @Override
    public void move() {
        System.out.println("Running");
    }
}