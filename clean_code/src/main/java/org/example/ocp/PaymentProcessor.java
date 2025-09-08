// class PaymentProcessor {
//     public void pay(String method) {
//         if (method.equals("paypal")) {
//             System.out.println("Paiement avec PayPal");
//         } else if (method.equals("creditcard")) {
//             System.out.println("Paiement avec Carte de crédit");
//         }
//     }
// }


interface PaymentMethod {
    void pay();
}

class PayPalPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paiement avec PayPal");
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paiement avec Carte de crédit");
    }
}

class PaymentProcessor {
    public void process(PaymentMethod method) {
        method.pay();
    }
}