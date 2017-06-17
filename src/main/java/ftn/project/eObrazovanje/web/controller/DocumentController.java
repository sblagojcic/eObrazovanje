package ftn.project.eObrazovanje.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hamcrest.core.IsInstanceOf;
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
import ftn.project.eObrazovanje.model.Professor;
import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.model.User;
import ftn.project.eObrazovanje.service.DocumentService;
import ftn.project.eObrazovanje.service.ProfessorService;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.service.UserService;
import ftn.project.eObrazovanje.web.dto.DocumentDTO;

@RestController
@RequestMapping(value = "api/documents")
public class DocumentController {

	private static final String UPLOADED_FOLDER = "./src/main/resources/public/upload/";

	@Autowired
	DocumentService documentService;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProfessorService professorService;
	
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
	
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")	@RequestMapping(method = RequestMethod.GET)
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
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN','ROLE_PROFESSOR')")
	@RequestMapping(value="/profilePic/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> uploadProfilePic(@RequestParam("file") MultipartFile file) {
        try {
        	if (file.isEmpty()) {
            	throw new IOException();
            }
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
	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN')")
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void downloadFiles(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) {
			Document document = documentService.findOne(id);						
			String filename = document.getPath();
			if(filename.contains(".pdf")){
				response.setContentType("application/pdf");
			}
			if(filename.contains(".jpg")|| filename.contains("jpeg")){
				response.setContentType("image/jpeg");
			}
			if(filename.contains(".png")){
				response.setContentType("image/png");
			}
            try {
    			if(filename != null) {
    				InputStream stream;
    				stream = new BufferedInputStream(
    						new FileInputStream(new File(filename)));
    				ServletOutputStream out;
    				out = response.getOutputStream();
    				byte[] bbuf = new byte[100];
    				int length = 0;
    				while ((stream != null) && ((length = stream.read(bbuf)) != -1))
    				   {
    				       out.write(bbuf,0,length);
    				   }
    				stream.close();
    				out.flush();
    				out.close();
    			}
    		} catch (Exception e1) {
    			e1.printStackTrace();
    		}
		}

	@PreAuthorize("hasAnyRole('ROLE_STUDENT','ROLE_ADMIN','ROLE_PROFESSOR')")
	@RequestMapping(value = "/downloadPicture/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> DownloadImagesProfesor(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		User user = userService.findOne(id);
		String src = "";
		if(user instanceof Student){
			src =((Student)user).getPicturePath();
		}else{
			src =((Professor)user).getPicturePath(); 
		}
		String srcRealtive = src.replace(".\\src\\main\\resources\\public\\upload\\" , "./upload/");
		Gson gson = new Gson();
		String path = gson.toJson(srcRealtive);
		return new ResponseEntity<String>(path, HttpStatus.OK);
	}
}
