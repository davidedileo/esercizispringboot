package com.develhope.demo_interceptor2.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Month {

    private int monthNumber;
    private String englishName;
    private String italianNAme;
    private String germanName;
}
