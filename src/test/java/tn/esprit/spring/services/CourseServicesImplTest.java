package tn.esprit.spring.services;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.TypeCourse;

import java.util.List;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class CourseServicesImplTest {

    @Autowired
    private CourseServicesImpl courseServices;

    @Test
    @Order(1)// Indique que ce test doit être exécuté en premier
    void searchCourses() {
        Course course1 = Course.builder()
                .level(1)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(50.0F)
                .timeSlot(1)
                .description("Cours collectif pour enfants")
                .build();

        Course course2 = Course.builder()
                .level(2)
                .typeCourse(TypeCourse.INDIVIDUAL)
                .price(100.0F)
                .timeSlot(2)
                .description("Cours individuel pour adultes")
                .build();

        courseServices.addCourse(course1);
        courseServices.addCourse(course2);
        List<Course> results = courseServices.searchCourses(1, null, null, null);
        Assertions.assertEquals(1, results.size(), "Il devrait y avoir 1 cours correspondant à la recherche");
        Assertions.assertEquals("Cours collectif pour enfants", results.get(0).getDescription(),
                "La description doit correspondre");
        courseServices.deleteCourse(course1.getNumCourse());
        courseServices.deleteCourse(course2.getNumCourse());


    }

    @Test
    void recommendCourses() {
        // Créer et enregistrer quelques cours pour les tests
        Course course1 = Course.builder()
                .title("Cours A")
                .level(1)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(100.0F)
                .description("Description du Cours A")
                .build();

        Course course2 = Course.builder()
                .title("Cours B")
                .level(1)
                .typeCourse(TypeCourse.COLLECTIVE_CHILDREN)
                .price(150.0F)
                .description("Description du Cours B")
                .build();

        Course course3 = Course.builder()
                .title("Cours C")
                .level(2)
                .typeCourse(TypeCourse.INDIVIDUAL)
                .price(200.0F)
                .description("Description du Cours C")
                .build();

        // Ajouter les cours au service (en supposant qu'il y a une méthode addCourse)
        courseServices.addCourse(course1);
        courseServices.addCourse(course2);
        courseServices.addCourse(course3);

        // Recommander des cours en fonction du type de cours
        List<Course> recommendedCourses = courseServices.recommendCourses(TypeCourse.COLLECTIVE_CHILDREN);

        // Assertions pour vérifier les résultats
        Assertions.assertEquals(2, recommendedCourses.size(), "Il devrait y avoir 2 cours recommandés");
        Assertions.assertTrue(recommendedCourses.stream().anyMatch(course -> course.getTitle().equals("Cours A")),
                "Le Cours A doit être dans les recommandations");
        Assertions.assertTrue(recommendedCourses.stream().anyMatch(course -> course.getTitle().equals("Cours B")),
                "Le Cours B doit être dans les recommandations");

        // Nettoyage des données de test
        courseServices.deleteCourse(course1.getNumCourse());
        courseServices.deleteCourse(course2.getNumCourse());
        courseServices.deleteCourse(course3.getNumCourse());
    }
}