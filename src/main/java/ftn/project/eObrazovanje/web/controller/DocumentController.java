package ftn.project.eObrazovanje.web.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import ftn.project.eObrazovanje.model.Document;
import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.service.DocumentService;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.web.dto.DocumentDTO;

@RestController
@RequestMapping(value = "api/documents")
public class DocumentController {

	private static final String UPLOADED_FOLDER = "./upload/";

	@Autowired
	DocumentService documentService;
	
	@Autowired
	StudentService studentService;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DocumentDTO> getProfessorRole(@PathVariable Long id) {
		Document document = documentService.findOne(id);
		if (document == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new DocumentDTO(document), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_ADMIN')")
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
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
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@RequestMapping(value="/upload", method = RequestMethod.POST)
    public ResponseEntity<Void> singleFileUpload(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	@RequestMapping(value="/uploadAngular", method = RequestMethod.POST)
    public ResponseEntity<String> singleFileUploadAngular(@RequestParam("file") MultipartFile file) {
        try {
        	if (file.isEmpty()) {
            	
            	throw new IOException();
            }
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            Gson gson = new Gson();
            String jsonTry = gson.toJson(path.toString());
            return new ResponseEntity<>(jsonTry,HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
