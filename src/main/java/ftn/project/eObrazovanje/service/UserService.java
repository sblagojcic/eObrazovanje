package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Transaction;
import ftn.project.eObrazovanje.model.User;
import ftn.project.eObrazovanje.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
    public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Page<User> findAll(Pageable page) {
		return userRepository.findAll(page);
	}

	public User save(User User) {
		return userRepository.save(User);
	}

	public void remove(Long id) {
		userRepository.delete(id);
	}

}
