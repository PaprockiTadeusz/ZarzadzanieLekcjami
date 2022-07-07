package com.paprocki.Zarzadzanie.Lekcjami.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {
    private String name;
    private String email;
    private String teacher;
    private Integer rate;
}
