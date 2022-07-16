package com.paprocki.Zarzadzanie.Lekcjami.dto;

import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDTO {
    private String name;
    private String email;
    private TeacherEntity teacher;
    private Integer rate;
    private List<LessonEntity> lessons;


}
