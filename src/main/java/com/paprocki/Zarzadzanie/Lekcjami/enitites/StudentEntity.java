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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Integer rate;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "student")
    private List<LessonEntity> lessons;

    public StudentEntity(long id, String name, String email, Integer rate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.rate = rate;
    }
}
