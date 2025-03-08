package com.gabo.best_travel.infraestructure.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class EmailHelper {
    private final JavaMailSender mailSender;

    public void sendMail(String to, String name, String product){
        MimeMessage message = mailSender.createMimeMessage();

        String htmlContent = this.readHtmlTemplate(name, product);

        try {
            message.setFrom(new InternetAddress(("gabocanseco@gmail.com")));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            message.setContent(htmlContent, "text/html; charset=utf-8");
            mailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error sending email", e);
        }
    }

    private String readHtmlTemplate(String name, String product){
        try (var lines = Files.lines(TEMPLATE_PATH)) {
            //reading all the file and concatenating
            var html = lines.collect(Collectors.joining());
            return html.replace("{name}", name).replace("{product}", product);
        } catch (IOException e) {
            log.error("Can read HTML template", e);
            throw new RuntimeException();
        }
    }

    private final Path TEMPLATE_PATH = Paths.get("src/main/resources/email/email_template.html");
}
