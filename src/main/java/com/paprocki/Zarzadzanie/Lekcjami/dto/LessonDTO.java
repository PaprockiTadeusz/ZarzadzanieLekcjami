package com.paprocki.Zarzadzanie.Lekcjami.dto;

import com.paprocki.Zarzadzanie.Lekcjami.enitites.StudentEntity;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LessonDTO {
    private long lessonId;
    private LocalDateTime date;
    private TeacherEntity teacher;
    private StudentEntity student;
    private String topic;
}