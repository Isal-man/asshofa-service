package com.asshofa.management.controller;

import com.asshofa.management.model.response.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.asshofa.management.model.pojo.EntitasPojo;

import com.asshofa.management.service.EntitasService;
import com.asshofa.management.util.DateHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EntitasController.class)
class EntitasControllerIntegrationTest {
    private static final String END_POINT = "/entitas";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EntitasService entitasService;

    Integer page = 1;
    Integer limit = 10;
    String sortField = "waktuRekam";
    String sortOrder = "DESC";
    String idEntitas = "1";
    String nip = "123456789012345";
    String date = String.valueOf(new DateHelper().getCurrentTimestamp());
    String version = "1.0.0";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    /*
     * Use case ketika seluruh parameter lengkap
     */
    @Test
    void getDatatable_And_Return_Success_Response() throws Exception {
        String urlDatatable = END_POINT + String.format("?page=%s&limit=%s&sortField=%s&sortOrder=%s", page, limit, sortField, sortOrder);
        doReturn(mockDatatableSuccessResponse()).when(entitasService).getDatatable(anyInt(), anyInt(), anyString(), anyString());

        final String expectedResponseContent = objectMapper.writeValueAsString(mockDatatableSuccessResponse());

        MvcResult mvcResult = mockMvc.perform(get(urlDatatable))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseContent))
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertNotNull(responseBody);

        DatatableResponse resp = objectMapper.readValue(responseBody.getBytes(StandardCharsets.UTF_8), DatatableResponse.class);
        assertNotNull(resp);

        assertEquals("Success", resp.getResult());
        assertEquals(ResponseMessage.DATA_FETCHED, resp.getDetail());
    }

    /*
     * Use case ketika parameter page dan limit tidak valid
     */
    @Test
    void getDatatable_With_Invalid_Parameter_And_Return_Failed_Response() throws Exception {
        String invalidPage = "a";
        String urlDatatable = END_POINT + String.format("?page=%s&limit=%s&sortField=%s&sortOrder=%s", invalidPage, limit, sortField, sortOrder);

        mockMvc.perform(get(urlDatatable))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentTypeMismatchException));
    }

    @Test
    void create_With_Invalid_Request_Body() throws Exception {
        String dataInJson = "{\"nibEntitas\":\"23232323121\",\"namaEntitas\":\"PT Abc\"}";
        MvcResult mvcResult = mockMvc.perform(post(END_POINT)
                .content(dataInJson)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertNotNull(responseBody);

        ErrorsResponse resp = objectMapper.readValue(responseBody.getBytes(StandardCharsets.UTF_8), ErrorsResponse.class);
        assertNotNull(resp);

        assertEquals("Error", resp.getResult());
        assertTrue(resp.getErrors().stream().anyMatch((a) -> a.toLowerCase().contains("id header")));
        assertTrue(resp.getErrors().stream().anyMatch((a) -> a.toLowerCase().contains("seri entitas")));

        verify(entitasService, times(0)).create(any(EntitasPojo.class));
    }

    private DatatableResponse<EntitasPojo> mockDatatableSuccessResponse() {
        List<EntitasPojo> list = new ArrayList<>();

        EntitasPojo dataSample = dataSample();
        list.add(dataSample);

        PageDataResponse<EntitasPojo> dataPage = new PageDataResponse<>(1, 10, 10, list);

        return new DatatableResponse<>(
                "Success", ResponseMessage.DATA_FETCHED, "/entitas", date, HttpStatus.OK.value(), version, dataPage
        );
    }

    private ResponseEntity<DefaultResponse> mockFailedResponse(String message) {
        return ResponseEntity.ok().body(new DefaultResponse(
                "Success", ResponseMessage.DATA_DELETED, "/entitas", date, HttpStatus.OK.value(), version
        ));
    }

    private EntitasPojo dataSample() {
        EntitasPojo dataSample = new EntitasPojo();
        dataSample.setIdEntitas("cc4a52a5-da7d-47f6-8e09-0a5aea5f1ebb");
        dataSample.setIdHeader("65eb77ca-9909-4123-94c3-68e9896dfba8");
        dataSample.setNamaEntitas("PT Sempat Berkah Tbk");
        dataSample.setNibEntitas("987654321");
        dataSample.setSeriEntitas(BigDecimal.valueOf(14));
        dataSample.setKodeEntitas("14");
        dataSample.setJenisEntitas("Pemegang Carnet");
        dataSample.setNipRekam("1234567890");
        dataSample.setWaktuRekam(Timestamp.valueOf("2024-02-01 13:45:00"));
        return dataSample;
    }
}
