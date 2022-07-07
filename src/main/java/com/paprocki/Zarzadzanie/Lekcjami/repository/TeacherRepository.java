package com.paprocki.Zarzadzanie.Lekcjami.repository;

import com.paprocki.Zarzadzanie.Lekcjami.enitites.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByEmail(String email);
//    @PostConstruct
//    public void init() {
//        teachers.add(new Teacher("Arkadiusz Domański", "arkadiusz.domanski@gmail.com"));
//        teachers.add(new Teacher("Zbigniew Wodzimski", "zgigniew.wodzimski@gmail.com"));
//    }

}
