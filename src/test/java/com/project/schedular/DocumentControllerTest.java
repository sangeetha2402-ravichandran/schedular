package com.project.schedular;

import com.project.schedular.controller.DocumentController;
import com.project.schedular.model.Documents;
import com.project.schedular.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DocumentControllerTest {

    private MockMvc mockMvc;
    private DocumentService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(DocumentService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new DocumentController(service)).build();
    }

    @Test
    void testGetAllDocuments() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(
                new Documents("1", "file1.txt", "text/plain", new byte[]{1}),
                new Documents("2", "file2.pdf", "application/pdf", new byte[]{2})
        ));
        mockMvc.perform(get("/api/documents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("file1.txt"));
    }

    @Test
    void testUploadDocument() throws Exception {
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", "Hello".getBytes());

        Documents doc = new Documents("1", "test.txt", "text/plain", "Hello".getBytes());
        when(service.uploadDocument(file)).thenReturn(doc);
        mockMvc.perform(multipart("/api/documents/upload").file(file))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("test.txt"));

    }
}
