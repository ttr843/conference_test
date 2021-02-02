package ru.waveaccess.test.conference.services;

public interface MailService {
    boolean confirmMail(String subject, String text, String email);
}
