package com.ag04smarts.sha.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class Person {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ApiModelProperty(value = "First name of a patient", required = true)
    protected String firstName;

    @ApiModelProperty(value = "Last name of a patient", required = true)
    protected String lastName;
}
