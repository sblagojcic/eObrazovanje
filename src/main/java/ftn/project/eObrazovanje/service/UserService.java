package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ftn.project.eObrazovanje.model.User;
import ftn.project.eObrazovanje.repository.UserRepository;

public class UserService implements UserRepository {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteInBatch(Iterable<User> arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public List<User> findAll(Sort arg0) {
		return null;
	}

	@Override
	public List<User> findAll(Iterable<Long> arg0) {
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> arg0) {
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> arg0, Sort arg1) {
		return null;
	}

	@Override
	public void flush() {
	}

	@Override
	public User getOne(Long arg0) {
		return null;
	}

	@Override
	public <S extends User> List<S> save(Iterable<S> arg0) {
		return null;
	}

	@Override
	public <S extends User> S saveAndFlush(S arg0) {
		return null;
	}

	@Override
	public Page<User> findAll(Pageable arg0) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(Long arg0) {
	}

	@Override
	public void delete(User arg0) {
	}

	@Override
	public void delete(Iterable<? extends User> arg0) {
	}

	@Override
	public void deleteAll() {
	}

	@Override
	public boolean exists(Long arg0) {
		return false;
	}

	@Override
	public User findOne(Long arg0) {
		return null;
	}

	@Override
	public <S extends User> S save(S arg0) {
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> arg0) {
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> arg0) {
		return false;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> arg0, Pageable arg1) {
		return null;
	}

	@Override
	public <S extends User> S findOne(Example<S> arg0) {
		return null;
	}

}
