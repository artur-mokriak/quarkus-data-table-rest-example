package com.data_table.rest_example.rest_service;

import com.data_table.rest_example.dto.CountryDto;
import com.data_table.rest_example.dto.CountryFindListResponseDto;
import com.data_table.rest_example.dto.PaginationDto;
import com.data_table.rest_example.entity.Country;
import com.data_table.rest_example.repository.CountryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CountryRestService {

    final CountryRepository countryRepository;

    @Inject
    public CountryRestService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryFindListResponseDto getFindList(String filterCode,
                                                  String filterName,
                                                  String sortField,
                                                  String sortOrder,
                                                  int pageIndex,
                                                  int pageSize) {
        List<Country> countryList;
        if ((filterCode != null && !filterCode.isEmpty()) && (filterName != null && filterName.isEmpty())) {
            countryList = countryRepository.findByLikeCode(filterCode, sortField, sortOrder, pageIndex, pageSize);
        } else if ((filterCode != null && filterCode.isEmpty()) && (filterName != null && !filterName.isEmpty())) {
            countryList = countryRepository.findByLikeName(filterName, sortField, sortOrder, pageIndex, pageSize);
        } else if ((filterCode != null && !filterCode.isEmpty()) && (filterName != null && !filterName.isEmpty())) {
            countryList = countryRepository.findByLikeCodeAndName(filterCode, filterName, sortField, sortOrder, pageIndex, pageSize);
        } else {
            countryList = countryRepository.findAll(sortField, sortOrder, pageIndex, pageSize);
        }
        List<CountryDto> result = countryList.stream().map(CountryDto::new).toList();
        int pageCount = (int) Math.ceil((double) countryList.size() / (double) pageSize);
        int totalRecords = (int) countryRepository.findAll().count();
        PaginationDto pagination = new PaginationDto(totalRecords, pageSize, pageIndex, pageCount);
        return new CountryFindListResponseDto(result, pagination);
    }
}
