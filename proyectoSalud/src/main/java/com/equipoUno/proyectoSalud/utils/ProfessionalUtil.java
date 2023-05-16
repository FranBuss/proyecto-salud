package com.equipoUno.proyectoSalud.utils;

import com.equipoUno.proyectoSalud.enumerations.Specialization;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ProfessionalUtil {

    private static final Map<String, Specialization> SPECIALIZATION_MAP;

    static {
        SPECIALIZATION_MAP = Collections.unmodifiableMap(buildSpecializationMap());
    }

    private ProfessionalUtil(){}

    private static Map<String, Specialization> buildSpecializationMap() {
        Map<String, Specialization> map = new HashMap<>();
        map.put("PEDIATRICS", Specialization.PEDIATRICS);
        map.put("GYNECOLOGY", Specialization.GYNECOLOGY);
        map.put("CLINIC", Specialization.CLINIC);
        map.put("CARDIOLOGY", Specialization.CARDIOLOGY);
        return map;
    }


    public static Specialization getSpecialization(String specializationString){
        if(!validateSpecialization(specializationString))
            throw new IllegalArgumentException("Especializacion invalida: " + specializationString);
        return SPECIALIZATION_MAP.get(specializationString);
    }

    private static boolean validateSpecialization(String specializationString){
        return SPECIALIZATION_MAP.containsKey(specializationString.toUpperCase());
    }

}
