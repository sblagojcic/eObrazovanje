package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Document;
import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.service.DocumentService;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.web.dto.DocumentDTO;

@RestController
@RequestMapping(value = "api/documents")
public class DocumentController {

	@Autowired
	DocumentService documentService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<DocumentDTO>> getDocuments() {
		List<Document> documents = documentService.findAll();
		List<DocumentDTO> documentsDTO = new ArrayList<DocumentDTO>();
		for (Document document : documents) {
			documentsDTO.add(new DocumentDTO(document));
		}
		return new ResponseEntity<>(documentsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Document>> getDocumentssPage(Pageable page) {
		Page<Document> documents = documentService.findAll(page);

		return new ResponseEntity<>(documents, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<DocumentDTO> saveDocument(@RequestBody DocumentDTO documentDTO) {
		Document document = new Document();
		document.setName(documentDTO.getName());
		document.setPath(documentDTO.getPath());
		Student student = studentService.findOne(documentDTO.getStudentID());
		document.setStudent(student);
		document = documentService.save(document);
		return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DocumentDTO> getProfessorRole(@PathVariable Long id) {
		Document document = documentService.findOne(id);
		if (document == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteResponsePayment(@PathVariable Long id) {
        Document document = documentService.findOne(id);
        if (document == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
        	documentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<DocumentDTO> updateProfessor(@RequestBody DocumentDTO documentDTO) {
		Document document = documentService.findOne(documentDTO.getId());
		if (document == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		document.setName(documentDTO.getName());
		document.setPath(documentDTO.getPath());
		Student student = studentService.findOne(documentDTO.getStudentID());
		document.setStudent(student);
		document = documentService.save(document);
		return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getFor/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<DocumentDTO>> getDocunemtForUser(@PathVariable Long id){
		List<Document> documents = documentService.findAll();
		List<DocumentDTO> documentsDTO = new ArrayList<DocumentDTO>();
		for(Document document : documents){
			if(document.getStudent().getId().equals(id)){
				documentsDTO.add(new DocumentDTO(document));
			}
		}
		return new ResponseEntity<>(documentsDTO, HttpStatus.OK);
	}
}
