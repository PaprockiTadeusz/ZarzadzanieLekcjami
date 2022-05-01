package com.paprocki.Zarzadzanie.Lekcjami.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Lesson {
    private long lessonId;
    private LocalDate date;
    private String teacherName;
    private String studentName;
    private String Topic;
}