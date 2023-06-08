package com.equipoUno.proyectoSalud.enumerations;

public enum Gender {
    FEMALE("Femenino"),
    MALE("Masculino");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}