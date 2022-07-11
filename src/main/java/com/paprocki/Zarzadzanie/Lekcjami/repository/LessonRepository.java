package com.paprocki.Zarzadzanie.Lekcjami.repository;

import com.paprocki.Zarzadzanie.Lekcjami.enitites.LessonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

    public Optional<LessonEntity> findById(Long id);


}
