package com.paprocki.Zarzadzanie.Lekcjami.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private String name;
    private String email;
    private String teacher;
    private int rate;
}
