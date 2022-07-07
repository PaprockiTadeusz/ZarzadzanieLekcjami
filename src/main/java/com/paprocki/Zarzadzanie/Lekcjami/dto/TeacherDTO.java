package com.paprocki.Zarzadzanie.Lekcjami.dto;

import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TeacherDTO {
    private String name;
    private String email;
    private List<LessonEntity> lessons;
}
