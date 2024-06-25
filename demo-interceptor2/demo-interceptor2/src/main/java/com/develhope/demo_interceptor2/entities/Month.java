package com.develhope.demo_interceptor2.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Month {

    private int monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;
}
