package com.codurance.dip;

public class EmailSenderImpl implements EmailSender {
    public void send(Email email) {
        System.out.print("To:"+email.getTo()+", Subject: "+email.getSubject()+", Message: "+email.getMessage());
    }


}
