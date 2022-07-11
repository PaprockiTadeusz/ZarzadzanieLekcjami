package com.paprocki.Zarzadzanie.Lekcjami.dto;

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
    private String teacherName;
    private String studentName;
    private String topic;
}