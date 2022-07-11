package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.dto.LessonDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import com.paprocki.Zarzadzanie.Lekcjami.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;

    public Optional<LessonDTO> getSingleLesson(long id) {
        return lessonRepository.findById(id)
                .map(o -> modelMapper.map(o, LessonDTO.class));
    }

    public List<LessonDTO> getAllLessons() {
        return lessonRepository.findAll().stream()
                .map(o -> modelMapper.map(o, LessonDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<LessonDTO> updateLesson(LessonDTO lessonDTO) {
        lessonRepository.save(modelMapper.map(lessonDTO, LessonEntity.class));
        return Optional.of(lessonDTO);
    }

//    public Optional<LessonDTO> updateLessonPartially(LessonDTO lessonDTO){
//        return lessonRepository.findById(lessonDTO.getLessonId())
//                .map(entity -> {
//                    Optional.of(lessonDTO.getDate()).ifPresent(entity::setDate);
//                    Optional.of(lessonDTO.getTeacherName()).ifPresent(entity::setTeacher);
//                    Optional.of(lessonDTO.getTopic()).ifPresent(entity::setTopic);
//                })
//    }
}
