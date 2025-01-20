package com.ckeditor.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ckeditor.entity.FileUpload;
import com.ckeditor.service.FileUploadService;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final String UPLOAD_DIR = "C:\\Users\\aryanbirla\\Desktop\\AIIMS-RAJKOT-FILES-DIRECTORY\\FILES_DIRECTORY\\";
    
    @Autowired
    private FileUploadService fileService;
    
    @GetMapping("/allFiles")
    public List<FileUpload> getAllFile(){
    	return fileService.getAllFiles();
    }
    
    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
    	fileService.deleteFile(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file,
    		@RequestParam("entry_user_id") Long entryUserId,
    		@RequestParam("entry_date") String entryDate, 
    		@RequestParam("last_modified_user_id") Long lastModifiedUserId,
    		@RequestParam("last_modified_date") String lastModifiedDate,
    		@RequestParam("valid") boolean valid,
    		@RequestParam("rendering_name") String rendering_name) throws IOException, ParseException {
    	
    	// Log received values to ensure correct data
        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("Entry User ID: " + entryUserId);
        System.out.println("Entry Date: " + entryDate);
        System.out.println("Last Modified User ID: " + lastModifiedUserId);
        System.out.println("Last Modified Date: " + lastModifiedDate);
        System.out.println("Valid: " + valid);
        System.out.println("Rendered Name: " + rendering_name);        
        
        // Create a new file name to prevent overwriting
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // Path to store the uploaded file
        Path path = Paths.get(UPLOAD_DIR + fileName);

        // Ensure the directory exists
        Files.createDirectories(path.getParent());

        // Save the file to the local server
        file.transferTo(path);

        // Create the file URL for access
        String fileUrl = "http://localhost:8080/api/files/" + fileName;
        
        // Changing the string type to date type 
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date entryDateParsed = dateformat.parse(entryDate);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // ISO-8601 format
        Date lastModifiedDateParsed = dateFormat.parse(lastModifiedDate);  // Parse last_modified_date string to Date
        
        // Populating my entity
        FileUpload fileEntry = new FileUpload();
        fileEntry.setFile_link(fileUrl);
        fileEntry.setFile_name(fileName);
        fileEntry.setEnter_user_id(entryUserId);
        fileEntry.setEntry_date(entryDateParsed);
        fileEntry.setLast_modified_user_id(lastModifiedUserId);
        fileEntry.setLast_modified_date(lastModifiedDateParsed);
        fileEntry.setValid(valid);
        fileEntry.setRendering_name(rendering_name);
        
        fileService.saveFile(fileEntry);

        return ResponseEntity.ok().body(new FileResponse(fileUrl));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> getFile(@PathVariable String fileName) throws IOException {
        // Path to retrieve the file
        Path path = Paths.get(UPLOAD_DIR + fileName);

        // Check if the file exists
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        // Get the file extension to determine content type (e.g., for PDFs)
        String fileExtension = getFileExtension(fileName);

        // Set content type based on file extension (you can add more cases here if needed)
        String contentType = "application/octet-stream"; // Default for binary files

        if ("pdf".equalsIgnoreCase(fileExtension)) {
            contentType = "application/pdf";
        } else if ("txt".equalsIgnoreCase(fileExtension)) {
            contentType = "text/plain";
        }
        else if ("jpg".equalsIgnoreCase(fileExtension) || "jpeg".equalsIgnoreCase(fileExtension)) {
            contentType = "image/jpeg";  // Handle JPG files specifically
        }

        // Serve the file from the local file system
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + fileName)
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(Files.readAllBytes(path));
    }

    @GetMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf() throws IOException {
        // Create a new document
        PDDocument document = new PDDocument();

        // Add a page to the document
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a content stream to add content to the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Start writing content to the PDF
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        contentStream.newLineAtOffset(100, 750);
        contentStream.showText("Hello, this is a PDF document generated by Spring Boot!");
        contentStream.endText();

        // Close the content stream
        contentStream.close();

        // Write the document to a ByteArrayOutputStream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        // Return the PDF as a response
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=document.pdf")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(baos.toByteArray());
    }

    // Helper method to get file extension
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    // DTO to return the file URL in the response
    public static class FileResponse {
        private String fileUrl;

        public FileResponse(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
