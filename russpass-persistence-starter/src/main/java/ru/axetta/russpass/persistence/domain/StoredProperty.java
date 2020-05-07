package ru.axetta.russpass.persistence.domain;

import lombok.Getter;

public enum StoredProperty {
    // email properties
    CONFIRM_EMAIL_SUBJECT("russpass.email.confirmemail.subject"),
    CONFIRM_EMAIL_CONTENT("russpass.email.confirmemail.content"),
    FORGOT_PASSWORD_SUBJECT("russpass.email.forgotpassword.subject"),
    FORGOT_PASSWORD_CONTENT("russpass.email.forgotpassword.content"),
    CHANGE_PASSWORD_SUBJECT("russpass.email.changepassword.subject"),
    CHANGE_PASSWORD_CONTENT("russpass.email.changepassword.content"),
    DELETE_ACCOUNT_SUBJECT("russpass.email.deleteaccount.subject"),
    DELETE_ACCOUNT_CONTENT("russpass.email.deleteaccount.content"),
    //token properties
    TOKEN_EXPIRE_HOURS_CONFIRM_EMAIL("russpass.token.expire.hours.confirmemail"),
    TOKEN_EXPIRE_HOURS_DELETE_ACCOUNT("russpass.token.expire.hours.deleteaccount"),
    TOKEN_EXPIRE_HOURS_CHANGE_PASSWORD("russpass.token.expire.hours.changepassword");

    @Getter
    private final String name;

    private StoredProperty(String name) {
        this.name = name;
    }
}
