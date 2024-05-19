package com.data_table.rest_example.lazy_data_model;

import com.data_table.rest_example.dto.CountryDto;
import com.data_table.rest_example.dto.CountryFindListResponseDto;
import com.data_table.rest_example.rest_client.CountryRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class CountryLazyDataModel extends LazyDataModel<CountryDto> {
    final CountryRestClient countryRestClient;

    @Inject
    public CountryLazyDataModel(@RestClient CountryRestClient countryRestClient) {
        this.countryRestClient = countryRestClient;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return filterBy.keySet().size();
    }

    @Override
    public List<CountryDto> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        int pageIndex = 0;
        if (filterBy.isEmpty()) {
            try {
                pageIndex = ((first + pageSize) / pageSize) - 1;
            } catch (ArithmeticException ignored) {
            }
        }
        Optional<SortMeta> sortMetaField = sortBy.values().stream().filter(sortMeta -> !sortMeta.getOrder().equals(SortOrder.UNSORTED)).findFirst();
        String sortField = "code";
        String sortOrder = "asc";
        if (sortMetaField.isPresent()) {
            sortField = sortMetaField.get().getField();
            if (sortMetaField.get().getOrder().isDescending()) {
                sortOrder = "desc";
            }
        }
        String filterName = "";
        String filterCode = "";
        if (filterBy.containsKey("name")) {
            filterName = (String) filterBy.get("name").getFilterValue();
        }
        if (filterBy.containsKey("code")) {
            filterCode = (String) filterBy.get("code").getFilterValue();
        }
        CountryFindListResponseDto findList = countryRestClient.findList(filterName, filterCode, sortField, sortOrder,
                pageIndex,  ((pageSize == 0) ? 10 : pageSize));
        setPageSize(pageSize);
        setRowCount(findList.getPagination().getTotalRecords());
        return findList.getList();
    }

    @Override
    public String getRowKey(CountryDto countryDto) {
        if (countryDto != null) {
            return (countryDto.getId() != null) ? countryDto.getId().toString() : null;
        }
        return null;
    }

    @Override
    public CountryDto getRowData(String rowKey) {
        List<CountryDto> list = getWrappedData();
        for (CountryDto portalCustomer : list) {
            if (portalCustomer.getId().toString().equals(rowKey)) {
                return portalCustomer;
            }
        }
        return null;
    }
}
