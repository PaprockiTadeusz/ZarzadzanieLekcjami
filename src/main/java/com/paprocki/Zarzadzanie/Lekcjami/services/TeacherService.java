package com.paprocki.Zarzadzanie.Lekcjami.services;

import com.paprocki.Zarzadzanie.Lekcjami.model.Teacher;
import com.paprocki.Zarzadzanie.Lekcjami.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Optional<Teacher> getSingleTeacher(String email){return teacherRepository.findTeacherByEmail(email);}
    public List<Teacher> getAllTeachers(){return teacherRepository.getAll();}

    public boolean addNewTeacher(Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findTeacherByEmail(teacher.getEmail());
        if(optionalTeacher.isPresent()){
            return false;
        } else {
            return teacherRepository.addTeacher(teacher);
        }
    }

    public Optional<Teacher> editTeacher(Teacher updatedTeacher) {
        return teacherRepository.updateTeacher(updatedTeacher);
    }

    public Optional<Teacher> editTeacherPartially(Teacher updatedTeacher){
        return teacherRepository.partiallyUpdateTeacher(updatedTeacher);
    }
}
