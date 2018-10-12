package com.divae.celebrity.databackend.controller;

import com.divae.celebrity.databackend.mockmvc.MockMvcFactory;
import com.divae.celebrity.databackend.model.CelebrityModel;
import com.divae.celebrity.databackend.services.CelebrityReaderService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainControllerUnitTest {

    private MockMvc mockMvc;

    private final CelebrityReaderService celebrityReaderService = mock(CelebrityReaderService.class);

    @Before
    public void setUp() {
        MainController mainController = new MainController(celebrityReaderService);
        mockMvc = MockMvcFactory.create(mainController);
    }


    @Test
    public void getCelebrity() throws Exception {

        when(celebrityReaderService.getModelFromDisk()).thenReturn(new CelebrityModel());


        mockMvc.perform(MockMvcRequestBuilders.get("/get-celebrity"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void getCelebrityFoundNothing() throws Exception {

        when(celebrityReaderService.getModelFromDisk()).thenReturn(null);


        mockMvc.perform(MockMvcRequestBuilders.get("/get-celebrity"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}