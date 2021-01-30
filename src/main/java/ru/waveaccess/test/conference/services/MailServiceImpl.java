package ru.waveaccess.test.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Value("${mail.from.username}")
    private String from;
    @Value("${mail.root.confirm}")
    private String rootConfirm;

    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final JavaMailSender sender;

    @Override
    public boolean confirmMail(String subject, String text, String email) {
        return sendLetter(subject, rootConfirm + text, email);
    }

    private boolean sendLetter(String subject, String text, String email) {
        Future<?> future = executorService.submit(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(from);
            sender.send(message);
        });
        return future.isDone();
    }
}
