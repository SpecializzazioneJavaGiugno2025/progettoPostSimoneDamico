package it.simo.aulab_post.services;

public interface  EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
