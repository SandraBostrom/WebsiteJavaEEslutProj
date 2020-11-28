package com.slutprojeeram.slutprojee.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class Admin implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", message = "Must be a valid email address!!")
    private String email;


    @Column(length=100)
    @NotBlank(message = "Must enter Password !!")
    @Size(min = 6,message = "Password must be between 6-12 characters !!")
    private String password;

    private  String role;

}
