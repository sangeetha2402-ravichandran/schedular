package com.project.schedular;

import com.project.schedular.model.Documents;
import com.project.schedular.repository.DocumentsRepository;
import com.project.schedular.repository.ScheduledTasksRepository;
import com.project.schedular.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DocumentServiceTests {

    private DocumentService documentService;
    private DocumentsRepository documentsRepository;


    @BeforeEach
    void setUp() {
        documentsRepository = Mockito.mock(DocumentsRepository.class);
        documentService = new DocumentService(documentsRepository);
    }

     @Test
    void testUploadDocument() throws IOException {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Hello World".getBytes());

        Documents doc = Documents.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .content(file.getBytes())
                .build();
        when(documentsRepository.save(doc)).thenReturn(doc);

        Documents saved = documentService.uploadDocument(file);
        assertEquals("test.txt", saved.getName());
        assertEquals("text/plain", saved.getType());
        verify(documentsRepository, times(1)).save(doc);
    }

    @Test
    void testGetAllDocuments() {
        List<Documents> mockDocs = Arrays.asList(new Documents("1", "file1.txt", "text/plain", new byte[]{1}),
                new Documents("2", "file2.pdf", "application/pdf", new byte[]{2}));
         when(documentsRepository.findAll()).thenReturn(mockDocs);
         List<Documents> documents = documentService.findAll();
         assertEquals(2, documents.size());
         verify(documentsRepository, times(1)).findAll();

    }
}


