package com.project.schedular.service;


import com.project.schedular.model.Documents;
import com.project.schedular.repository.DocumentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentsRepository documentsRepository;

    public DocumentService(DocumentsRepository documentsRepository) {
        this.documentsRepository = documentsRepository;
    }

    public Documents uploadDocument(MultipartFile file) throws IOException {
        Documents document = new Documents().builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .content(file.getBytes())
                .build();
       return documentsRepository.save(document);
    }

    public List<Documents> findAll() {
        return  documentsRepository.findAll();

    }


}
