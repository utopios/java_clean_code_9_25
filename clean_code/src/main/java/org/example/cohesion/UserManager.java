package org.example.cohesion;
public class UserManager  {

    private final EmailService emailService;

    public UserManager(EmailService emailService) {
        this.emailService = emailService;
    }

    public void createUser() {

    }

    /*public void sendEmail() {

    }*/
}