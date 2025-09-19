package com.project.schedular.controller;


import com.project.schedular.model.Documents;
import com.project.schedular.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;


    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public List<Documents> getAllDocuments() {
        return documentService.findAll();

    }

    @PostMapping("/upload")
    public ResponseEntity<Documents> uploadDocument(@RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(documentService.uploadDocument(file));


    }




}
