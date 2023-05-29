package com.equipoUno.proyectoSalud.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "content_type")
    private String contentType;
    private String name;


    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Size(max = 5 * 1024 * 1024) //Maximo 5MB
    private byte[] content;

    public Image(){}

}
