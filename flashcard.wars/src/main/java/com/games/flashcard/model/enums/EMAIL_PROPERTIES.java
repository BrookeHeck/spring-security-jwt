package com.games.flashcard.model.enums;

public enum EMAIL_PROPERTIES {
    SMTP_HOST("mail.smtp.host"),
    SMTP_AUTH("mail.smtp.auth"),
    SMTP_PORT("mail.smtp.port"),
    SMTP_SERVER("mail.smtp.server"),
    SMTP_STARTTLS_ENABLE("mail.smtp.starttls.enable"),
    SMTP_STARTTLS_REQUIRED("mail.smtp.starttls.required");

    private final String property;


    EMAIL_PROPERTIES(String property) {this.property = property;}

    public String getProperty() {return property;}
}
