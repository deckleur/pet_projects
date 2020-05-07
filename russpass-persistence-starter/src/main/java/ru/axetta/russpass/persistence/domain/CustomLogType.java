package ru.axetta.russpass.persistence.domain;

public enum CustomLogType {
    USER_CREATED("CREATED"),
    ACCESS_TOKEN("ACCESS_TOKEN"),
    REFRESH_TOKEN("REFRESH_TOKEN"),
    GOOGLE_LOGIN("GOOGLE_LOGIN"),
    FACEBOOK_LOGIN("FACEBOOK_LOGIN");

    private String code;

    private CustomLogType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
