package com.paprocki.Zarzadzanie.Lekcjami.enitites;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "student")
@Builder
public class StudentEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer rate;
    @Column(unique = true)
    private String teacher;

    @OneToMany(mappedBy="student")
    private List<LessonEntity> lessons;
}
