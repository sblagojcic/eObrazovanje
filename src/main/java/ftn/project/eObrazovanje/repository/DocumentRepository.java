package ftn.project.eObrazovanje.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.eObrazovanje.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
