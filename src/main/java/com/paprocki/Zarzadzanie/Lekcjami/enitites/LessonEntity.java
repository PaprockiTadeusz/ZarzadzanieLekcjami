package com.paprocki.Zarzadzanie.Lekcjami.enitites;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "lessons")
@Builder
public class LessonEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private LocalDateTime date;

    @ManyToOne
    private TeacherEntity teacher;

    @ManyToOne
    private StudentEntity student;

    private String topic;
}
