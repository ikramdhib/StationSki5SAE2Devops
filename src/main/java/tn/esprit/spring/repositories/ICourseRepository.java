package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.entities.CourseDTO;


public interface ICourseRepository extends JpaRepository<CourseDTO, Long> {



}
