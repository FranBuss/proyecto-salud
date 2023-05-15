package com.equipoUno.proyectoSalud.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class PatientDTO extends UserDTO {

    private String healthInsurance;
    private String contact;

}
