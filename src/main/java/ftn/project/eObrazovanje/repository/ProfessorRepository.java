package ftn.project.eObrazovanje.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	Page<Professor> findAllByNameLikeOrUserNameLikeOrLastNameLike(String name,String userName, String lastName, Pageable page);

}
