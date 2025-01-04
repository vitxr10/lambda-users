package br.com.vitxr.lambdausers.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private int age;
    private double weight;
    private double heigth;
}
