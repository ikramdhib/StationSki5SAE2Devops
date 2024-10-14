package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entities.*;

import java.util.List;

public interface IRegistrationRepository extends JpaRepository<RegistrationTDO, Long> {

    long countByCourseAndNumWeek(CourseDTO course, int numWeek);
    @Query("select reg.numWeek from RegistrationTDO reg " +
            "join InstructorTDO ins " +
            "on reg.course member ins.courses " +
            "where ins.numInstructor = :idIns and reg.course.support = :support")
    List<Integer> numWeeksCourseOfInstructorBySupport(@Param("idIns") Long numInstructor, @Param("support") Support support);

    @Query("select count(distinct r) from RegistrationTDO r " +
            "where r.numWeek = ?1 and r.skier.numSkier = ?2 and r.course.numCourse = ?3")
    long countDistinctByNumWeekAndSkier_NumSkierAndCourse_NumCourse(int numWeek, Long numSkier, Long numCourse);







}
