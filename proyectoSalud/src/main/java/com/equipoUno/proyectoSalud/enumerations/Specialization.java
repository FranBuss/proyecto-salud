package com.equipoUno.proyectoSalud.enumerations;

public enum Specialization {
    PEDIATRICS("Pediatria"),
    GYNECOLOGY("Ginecologia"),
    CLINIC("Clinica"),
    CARDIOLOGY("Cardiologia");

    private String displayName;

    Specialization(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    public static Specialization fromString(String value){
        for (Specialization specialization : Specialization.values()) {
            if (specialization.displayName.equalsIgnoreCase(value)){
                return specialization;
            }
        }
        throw new IllegalArgumentException("Invalid specialization value: " + value);
    }
}
