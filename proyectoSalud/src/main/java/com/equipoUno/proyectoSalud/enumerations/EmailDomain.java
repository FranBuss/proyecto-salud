package com.equipoUno.proyectoSalud.enumerations;

public enum EmailDomain {
    GMAIL("gmail.com"),
    GMAIL_AR("gmail.com.ar"),
    OUTLOOK("outlook.com"),
    OUTLOOK_AR("outlook.com.ar"),
    HOTMAIL("hotmail.com"),
    YAHOO("yahoo.com"),
    YAHOO_AR("yahoo.com.ar");

    private String value;

    EmailDomain(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
