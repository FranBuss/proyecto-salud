package com.equipoUno.proyectoSalud.enumerations;

public enum EmailSuffix {

    GMAIL("@gmail.com"),
    HOTMAIL("@hotmail.com"),
    YAHOO("@yahoo.com"),

    OUTLOOK("@outlook.com");

    private final String suffix;

    private EmailSuffix(String suffix){
        this.suffix = suffix;
    }

    public String getSuffix(){
        return suffix;
    }


}
