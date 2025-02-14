package com.asshofa.management.service;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.TdEntitas;
import com.asshofa.management.model.entity.TrEntitas;
import com.asshofa.management.model.pojo.EntitasPojo;
import com.asshofa.management.model.response.*;

import com.asshofa.management.repository.EntitasRepository;
import com.asshofa.management.repository.RefEntitasRepository;
import com.asshofa.management.service.impl.EntitasServiceImpl;
import com.asshofa.management.util.DateHelper;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EntitasServiceTest {

    @Mock
    EntitasRepository entitasRepository;

    @Mock
    RefEntitasRepository refEntitasRepository;

    @Mock
    private HeaderHolder headerHolder;

    @Mock
    LoggingHolder loggingHolder;

    @InjectMocks
    EntitasServiceImpl entitasService;

    int page = 1;
    int limit = 10;
    String sortField = "waktuRekam";
    String sortOrder = "DESC";
    String idEntitas = "1";
    String nip = "123456789012345";
    String date = String.valueOf(new DateHelper().getCurrentDate());
    String version = "1.0.0";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Test Fungsi Get Datatable jika semua kondisi terpenuhi")
    @Test
    void test_getDatatable() {
        List<TdEntitas> entitasList = new ArrayList<>();
        Page<TdEntitas> pageEntitas = new PageImpl<>(entitasList);

        List<EntitasPojo> pojoList = pageEntitas.getContent().stream().map(this::toPojo).collect(Collectors.toList());

        when(loggingHolder.getPath()).thenReturn("/entitas");
        when(loggingHolder.getDate()).thenReturn(date);
        when(loggingHolder.getVersion()).thenReturn(version);

        when(entitasRepository.findAll(any(Pageable.class))).thenReturn(pageEntitas);

        // When
        DatatableResponse<EntitasPojo> response = entitasService.getDatatable(page, limit, sortField, sortOrder);

        PageDataResponse<EntitasPojo> pageResp = new PageDataResponse<>(page, limit, pojoList.size(), pojoList);

        // Then
        verify(entitasRepository, times(1)).findAll(any(Pageable.class));
        assertNotNull(response);

        assertEquals("Success", response.getResult());
        assertEquals("Data(s) successfully fetched.", response.getDetail());
        assertEquals(date, response.getDate());
        assertEquals("/entitas", response.getPath());
        assertEquals(200, response.getCode());
        assertEquals("1.0.0", response.getVersion());
        assertEquals(1, pageResp.getPage());
        assertEquals(10, pageResp.getLimit());
        assertEquals(0, pageResp.getTotal());
        assertEquals(pojoList, pageResp.getList());
    }

    @DisplayName("Test Fungsi Find One Data")
    @Test
    void test_findOne() {
        TdEntitas entitas = new TdEntitas();
        entitas.setIdEntitas(idEntitas);
        when(entitasRepository.findById(idEntitas)).thenReturn(Optional.of(entitas));

        when(loggingHolder.getPath()).thenReturn("/entitas/findOne");
        when(loggingHolder.getDate()).thenReturn(date);
        when(loggingHolder.getVersion()).thenReturn(version);

        DataResponse<EntitasPojo> response = entitasService.findOne(idEntitas);

        verify(entitasRepository, times(1)).findById(idEntitas);
        assertNotNull(response);

        assertEquals("Success", response.getResult());
        assertEquals(ResponseMessage.DATA_FETCHED, response.getDetail());
        assertEquals(date, response.getDate());
        assertEquals("/entitas/findOne", response.getPath());
        assertEquals(200, response.getCode());
        assertEquals("1.0.0", response.getVersion());
        assertEquals(idEntitas, response.getData().getIdEntitas());
        assertNotNull(response.getData());
    }

    @DisplayName("Test fungsi Data Not Found pada method findOne")
    @Test
    void test_findOne_Not_Found() throws Exception {
        when(entitasRepository.findById(idEntitas)).thenReturn(Optional.empty());

        NotFoundException e = assertThrows(NotFoundException.class,
                () -> {
                    entitasService.findOne(idEntitas);
                });
        assertEquals(ResponseMessage.DATA_NOT_FOUND, e.getMessage());
    }

    @DisplayName("Test Fungsi Create Data")
    @Test
    void test_create() {
        TdEntitas entitas = new TdEntitas();
        when(headerHolder.getNip()).thenReturn(nip);
        when(entitasRepository.save(any(TdEntitas.class))).thenReturn(entitas);

        when(loggingHolder.getPath()).thenReturn("/entitas");
        when(loggingHolder.getDate()).thenReturn(date);
        when(loggingHolder.getVersion()).thenReturn(version);

        EntitasPojo entitasPojo = toPojo(entitas);
        DataResponse<EntitasPojo> response = entitasService.create(entitasPojo);

        verify(entitasRepository).save(any(TdEntitas.class));
        assertNotNull(response);

        assertEquals("Success", response.getResult());
        assertEquals(ResponseMessage.DATA_CREATED, response.getDetail());
        assertEquals(date, response.getDate());
        assertEquals("/entitas", response.getPath());
        assertEquals(200, response.getCode());
        assertEquals("1.0.0", response.getVersion());
    }

    @DisplayName("Test Fungsi Update Data")
    @Test
    void test_update() {
        Optional<TdEntitas> entitas = Optional.of(new TdEntitas());
        EntitasPojo entitasPojo = toPojo(entitas.get());

        when(entitasRepository.findById(idEntitas)).thenReturn(entitas);
        when(entitasRepository.save(any(TdEntitas.class))).thenReturn(entitas.get());

        when(loggingHolder.getPath()).thenReturn("/entitas");
        when(loggingHolder.getDate()).thenReturn(date);
        when(loggingHolder.getVersion()).thenReturn(version);

        DataResponse<?> response = entitasService.update(idEntitas, entitasPojo);

        verify(entitasRepository).findById(idEntitas);
        verify(entitasRepository).save(any(TdEntitas.class));
        assertNotNull(response);

        assertEquals("Success", response.getResult());
        assertEquals(ResponseMessage.DATA_UPDATED, response.getDetail());
        assertEquals(date, response.getDate());
        assertEquals("/entitas", response.getPath());
        assertEquals(200, response.getCode());
        assertEquals("1.0.0", response.getVersion());
    }

    @DisplayName("Test Fungsi Delete Data")
    @Test
    void test_delete() {
        doNothing().when(entitasRepository).deleteById(any());

        when(loggingHolder.getPath()).thenReturn("/entitas");
        when(loggingHolder.getDate()).thenReturn(date);
        when(loggingHolder.getVersion()).thenReturn(version);

        DefaultResponse response = entitasService.delete(idEntitas);

        verify(entitasRepository).deleteById(idEntitas);
        assertNotNull(response);

        assertEquals("Success", response.getResult());
        assertEquals(ResponseMessage.DATA_DELETED, response.getDetail());
        assertEquals(date, response.getDate());
        assertEquals("/entitas", response.getPath());
        assertEquals(200, response.getCode());
        assertEquals("1.0.0", response.getVersion());
    }

    private EntitasPojo toPojo(TdEntitas data) {
        return EntitasPojo.builder()
                .idEntitas(data.getIdEntitas())
                .idHeader(data.getIdHeader())
                .namaEntitas(data.getNamaEntitas())
                .kodeEntitas(data.getRefEntitas() != null ? data.getRefEntitas().getKodeEntitas(): null)
                .jenisEntitas(data.getRefEntitas() != null ? data.getRefEntitas().getNamaEntitas(): null)
                .nibEntitas(data.getNibEntitas())
                .seriEntitas(data.getSeriEntitas())
                .nipRekam(data.getNipRekam())
                .waktuRekam(data.getWaktuRekam())
                .build();
    }

    private TdEntitas toEntity(EntitasPojo source, TdEntitas destination) {
        Optional<TrEntitas> refEntitas = refEntitasRepository.findById(source.getKodeEntitas());
        destination.setIdHeader(source.getIdHeader());
        destination.setRefEntitas(refEntitas.orElse(null));
        destination.setNamaEntitas(source.getNamaEntitas());
        destination.setNibEntitas(source.getNibEntitas());
        destination.setSeriEntitas(source.getSeriEntitas());
        return destination;
    }
}
