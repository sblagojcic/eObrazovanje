package ftn.project.eObrazovanje.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findUserByUserName(String username);
}
