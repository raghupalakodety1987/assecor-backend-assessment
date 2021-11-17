package com.assessment.assescor.repository;

import java.util.List;
import java.util.Optional;

import com.assessment.assescor.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findById(Long Id);
    List<Person> findByColor(String Color);
}

