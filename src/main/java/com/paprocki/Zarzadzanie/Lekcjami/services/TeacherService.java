package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.dto.TeacherDTO;
import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
import com.paprocki.Zarzadzanie.Lekcjami.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelmapper;

    public Optional<TeacherDTO> getSingleTeacher(String email) {
        return teacherRepository.findByEmail(email)
                .map(o -> modelmapper.map(o, TeacherDTO.class));
    }

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(o -> modelmapper.map(o, TeacherDTO.class))
                .collect(Collectors.toList());
    }

    public boolean addNewTeacher(TeacherDTO teacherDTO) {
        if (teacherRepository.findByEmail(teacherDTO.getEmail()).isEmpty()) {
            teacherRepository.save(modelmapper.map(teacherDTO, TeacherEntity.class));
            return true;
        }
        return false;
    }

    public Optional<TeacherDTO> editTeacher(TeacherDTO teacherDTO) {
        teacherRepository.save(modelmapper.map(teacherDTO, TeacherEntity.class));
        return Optional.of(teacherDTO);
    }

    public Optional<TeacherDTO> editTeacherPartially(TeacherDTO teacherDTO) {
        return teacherRepository.findByEmail(teacherDTO.getEmail())
                .map(entity -> {
                    Optional.of(teacherDTO.getEmail()).ifPresent(entity::setEmail);
                    Optional.of(teacherDTO.getName()).ifPresent(entity::setName);
                    Optional.of(teacherDTO.getLessons()).ifPresent(entity::setLessons);
                    TeacherEntity save = teacherRepository.save(entity);
                    return Optional.of(modelmapper.map(save, TeacherDTO.class));
                })
                .orElseGet(Optional::empty);
    }
}
