package com.assessment.assescor.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Getter
@Setter
public class Person{//(
    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long id;
    private     String name;
    private String lastName;
    private String zipCode;
    private String city;
    private String color;

    public Person(long id, String name, String lastName, String zipCode, String city, String color) {
        this.id = id;
        this.city = city;
        this.color = color;
        this.lastName = lastName;
        this.name = name;
        this.zipCode = zipCode;

    }

    public Person(){

    }

}
