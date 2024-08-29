package com.gym.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "province")
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


}
