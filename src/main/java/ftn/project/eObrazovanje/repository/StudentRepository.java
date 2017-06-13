package ftn.project.eObrazovanje.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Student;


public interface StudentRepository extends JpaRepository<Student, Long> {

	Page<Student> findAllByNameLikeOrUserNameLikeOrLastNameLike(String name,String username, String lastname, Pageable page);

}
