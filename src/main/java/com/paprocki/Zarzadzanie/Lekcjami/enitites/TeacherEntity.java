package com.paprocki.Zarzadzanie.Lekcjami.enitites;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "teacher")
@Builder
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "teacher")
    private List<LessonEntity> lessons;
}
