package ca.sheridancollege.teixerya.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Drink {

    private int id;
    private String name;
    private String main;
    private double amount1;
    private String second;
    private double amount2;
    private String directions;
}
