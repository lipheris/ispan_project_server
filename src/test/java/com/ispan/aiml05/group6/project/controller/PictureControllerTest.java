package com.ispan.aiml05.group6.project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ispan.aiml05.group6.project.dto.PictureDTO;
import com.ispan.aiml05.group6.project.service.PictureService;

@SpringBootTest
@AutoConfigureMockMvc
public class PictureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PictureService pictureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPictureById() throws Exception {
        PictureDTO pictureDto = PictureDTO.builder().id(1L).build();
        
        when(pictureService.getPictureById(1L)).thenReturn(pictureDto);

        mockMvc.perform(get("/api/pictures/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(pictureDto)));
    }

    @Test
    void testGetPictures() throws Exception {
        PictureDTO pictureDTO = new PictureDTO();
        List<PictureDTO> pictureDTOs = Arrays.asList(pictureDTO);
        when(pictureService.getAllPictures()).thenReturn(pictureDTOs);

        mockMvc.perform(get("/api/pictures"))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(pictureDTOs)));
    }

    @Test
    void testCreatePicture() throws Exception {
        PictureDTO pictureDTO = new PictureDTO();
        when(pictureService.createPicture(any(PictureDTO.class))).thenReturn(pictureDTO);

        mockMvc.perform(post("/api/pictures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pictureDTO)))
            .andExpect(status().isCreated())
            .andExpect(content().json(objectMapper.writeValueAsString(pictureDTO)));
    }

    @Test
    void testReplacePicture() throws Exception {
        PictureDTO pictureDTO = new PictureDTO();
        when(pictureService.existsById(1L)).thenReturn(true);
        when(pictureService.replacePicture(eq(1L), any(PictureDTO.class))).thenReturn(pictureDTO);

        mockMvc.perform(put("/api/pictures/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pictureDTO)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(pictureDTO)));
    }

    @Test
    void testUpdatePicture() throws Exception {
        PictureDTO pictureDTO = new PictureDTO();
        when(pictureService.updatePicture(eq(1L), any(PictureDTO.class))).thenReturn(pictureDTO);

        mockMvc.perform(patch("/api/pictures/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(pictureDTO)))
            .andExpect(status().isOk())
            .andExpect(content().json(objectMapper.writeValueAsString(pictureDTO)));
    }

    @Test
    void testDeletePicture() throws Exception {
        mockMvc.perform(delete("/api/pictures/1"))
            .andExpect(status().isNoContent());
    }
}

