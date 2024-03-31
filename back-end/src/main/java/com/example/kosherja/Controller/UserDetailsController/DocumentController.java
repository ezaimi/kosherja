package com.example.kosherja.Controller.UserDetailsController;


import com.example.kosherja.Model.UserDetails.Documents;
import com.example.kosherja.Repo.UserDetailsRepo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentRepo documentRepository;

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        List<Documents> documents = documentRepository.findAll();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documents> getDocumentById(@PathVariable String id) {
        Optional<Documents> document = documentRepository.findById(id);
        return document.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Documents> createDocument(@RequestBody Documents document) {
        Documents savedDocument = documentRepository.save(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Documents> updateDocument(@PathVariable String id, @RequestBody Documents document) {
        Optional<Documents> existingDocumentOptional = documentRepository.findById(id);

        if (existingDocumentOptional.isPresent()) {
            Documents existingDocument = existingDocumentOptional.get();

            // updating existing document with new values
            existingDocument.setType(document.getType());
            existingDocument.setNumber(document.getNumber());
            // updating other attributes as needed

            Documents updatedDocument = documentRepository.save(existingDocument);
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        Optional<Documents> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            documentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
