package com.asshofa.management.service.impl;

import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.model.entity.TdEntitas;
import com.asshofa.management.model.entity.TrEntitas;
import com.asshofa.management.model.pojo.EntitasPojo;
import com.asshofa.management.model.response.*;

import com.asshofa.management.repository.EntitasRepository;
import com.asshofa.management.repository.RefEntitasRepository;
import com.asshofa.management.service.EntitasService;
import com.asshofa.management.util.DateHelper;
import com.asshofa.management.util.interceptor.HeaderHolder;
import com.asshofa.management.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EntitasServiceImpl implements EntitasService {
    private final EntitasRepository entitasRepository;
    private final RefEntitasRepository refEntitasRepository;
    private final HeaderHolder headerHolder;
    private final LoggingHolder loggingHolder;

    private static final Logger log = LogManager.getLogger(EntitasServiceImpl.class);

    public EntitasServiceImpl(EntitasRepository entitasRepository, RefEntitasRepository refEntitasRepository, HeaderHolder headerHolder, LoggingHolder loggingHolder) {
        this.entitasRepository = entitasRepository;
        this.refEntitasRepository = refEntitasRepository;
        this.headerHolder = headerHolder;
        this.loggingHolder = loggingHolder;
    }

    @Override
    public DatatableResponse<EntitasPojo> getDatatable(int page, int limit, String sortField, String sortOrder) {
        try {
            String[] allowedOrder = {"waktuRekam"};
            Sort sort = Sort.by(Sort.Direction.ASC, "waktuRekam");
            if ( Arrays.asList(allowedOrder).contains(sortField) ) {
                sort = sortOrder.equalsIgnoreCase("ASC")  ? Sort.by(Sort.Direction.ASC, sortField) : Sort.by(Sort.Direction.DESC, sortField);
            }
            Pageable pageable = PageRequest.of(page -1, limit, sort);
            Page<TdEntitas> pageResult = entitasRepository.findAll(pageable);
            return toDatatablePojo(pageResult, page);
        } catch (Exception e) {
            log.error("Error when get datatable entitas.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<EntitasPojo> findOne(String idEntitas) {
        try {
            Optional<TdEntitas> data = entitasRepository.findById(idEntitas);
            if ( data.isPresent() ) {
                return new DataResponse<>(ResponseMessage.PESAN, ResponseMessage.DATA_FETCHED, loggingHolder.getPath(), loggingHolder.getDate(), HttpStatus.OK.value(), loggingHolder.getVersion(), toPojo(data.get()));
            } else {
                throw new NotFoundException(ResponseMessage.DATA_NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error when get detail entitas.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<EntitasPojo> create(EntitasPojo entitas) {
        try {
            TdEntitas dataInsert = new TdEntitas();
            dataInsert.setIdEntitas(UUID.randomUUID().toString());
            dataInsert.setNipRekam(headerHolder.getNip());
            dataInsert.setWaktuRekam(new DateHelper().getCurrentTimestamp());

            TdEntitas data = entitasRepository.save(toEntity(entitas, dataInsert));

            return new DataResponse<>(ResponseMessage.PESAN, ResponseMessage.DATA_CREATED, loggingHolder.getPath(), loggingHolder.getDate(), HttpStatus.OK.value(), loggingHolder.getVersion(), toPojo(data));
        } catch (Exception e) {
            log.error("Error when create a entitas.", e);
            throw e;
        }
    }

    @Override
    public DataResponse<EntitasPojo> update(String idEntitas, EntitasPojo entitas) {
        try {
            Optional<TdEntitas> dataUpdate = entitasRepository.findById(idEntitas);
            if ( dataUpdate.isPresent() ) {
                TdEntitas data = entitasRepository.save(toEntity(entitas, dataUpdate.get()));
                return new DataResponse<>(ResponseMessage.PESAN, ResponseMessage.DATA_UPDATED, loggingHolder.getPath(), loggingHolder.getDate(), HttpStatus.OK.value(), loggingHolder.getVersion(), toPojo(data));
            } else {
                throw  new NotFoundException("Data tidak ditemukan");
            }
        } catch (Exception e) {
            log.error("Error when update a entitas.", e);
            throw e;
        }
    }

    @Override
    public DefaultResponse delete(String idEntitas) {
        try {
            entitasRepository.deleteById(idEntitas);
            return new DefaultResponse(ResponseMessage.PESAN, ResponseMessage.DATA_DELETED, loggingHolder.getPath(), loggingHolder.getDate(), HttpStatus.OK.value(), loggingHolder.getVersion());
        } catch (Exception e) {
            log.error("Error when delete a entitas.", e);
            throw e;
        }
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

    private DatatableResponse<EntitasPojo> toDatatablePojo(Page<TdEntitas> datas, int page) {

        List<EntitasPojo> list = datas.getContent().stream().map(this::toPojo).collect( Collectors.toList() );
        PageDataResponse<EntitasPojo> dataPage = new PageDataResponse<>(page, datas.getSize(),
                (int) datas.getTotalElements(), list);
        return new DatatableResponse<>(
                ResponseMessage.PESAN, ResponseMessage.DATA_FETCHED, loggingHolder.getPath(), loggingHolder.getDate(), HttpStatus.OK.value(), loggingHolder.getVersion(), dataPage
        );
    }
}
