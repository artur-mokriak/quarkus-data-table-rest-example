package com.data_table.rest_example.ui_view_controller;

import com.data_table.rest_example.dto.CountryDto;
import com.data_table.rest_example.dto.CountryFindListResponseDto;
import com.data_table.rest_example.lazy_data_model.CountryLazyDataModel;
import com.data_table.rest_example.rest_client.CountryRestClient;
import jakarta.el.ELContext;
import jakarta.el.ExpressionFactory;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.SortEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import java.io.Serializable;
import java.util.*;

@Named
@RequestScoped
public class CountryListViewController extends BaseViewController implements Serializable {
    private static final String DEFAULT_SORT_BY_COLUMN_NAME = "code";
    private static final String DEFAULT_SORT_ORDER_COLUMN_NAME = "asc";
    private List<CountryDto> countryList;
    private CountryDto selectedCountry;
    private DataTable dataTable;
    final CountryRestClient countryRestClient;
    final CountryLazyDataModel countryLazyDataModel;
    private Map<String, SortMeta> sortBy = new HashMap<>();

    @Inject
    public CountryListViewController(@RestClient CountryRestClient countryRestClient,
                                     CountryLazyDataModel countryLazyDataModel) {
        this.countryRestClient = countryRestClient;
        this.countryLazyDataModel = countryLazyDataModel;
    }

    public CountryLazyDataModel getCountryLazyDataModel() {
        return countryLazyDataModel;
    }

    public void setupData() {
        selectedCountry = new CountryDto();
        CountryFindListResponseDto findList = countryRestClient.findAll(0, 10);
        countryList = findList.getList();
    }

    public void onSort(SortEvent sortEvent) {
        this.sortBy = sortEvent.getSortBy();
        Optional<SortMeta> sortMetaField = this.sortBy.values().stream().filter(sortMeta -> !sortMeta.getOrder().equals(SortOrder.UNSORTED)).findFirst();
        if (sortMetaField.isPresent()) {
            SortMeta sortMeta = sortMetaField.get();
            String fieldName = sortMeta.getField();
            SortOrder sortOrder = sortMeta.getOrder();
            setSessionParameter("country_list.table.sort_by_column_name", fieldName);
            setSessionParameter("country_list.table.sort_order_column_name", sortOrder.isAscending() ? "asc" : "desc");
        }
    }

    public void setDataTable(DataTable dataTable) {
        if (dataTable != null) {
            this.sortBy = dataTable.getSortByAsMap();
            ExpressionFactory expressionFactory = ExpressionFactory.newInstance();
            ELContext elContext = facesContext.getCurrentInstance().getELContext();
            String expression = "#{varCountry." + getColumnNameSortBy() + "}";
            dataTable.setValueExpression("sortBy", expressionFactory.createValueExpression(elContext, expression, Object.class));
            dataTable.setSortByAsMap(this.sortBy);
        }
        this.dataTable = dataTable;
    }

    private String getColumnNameSortOrder() {
        String sortOrder = (String) getSessionParameter("country_list.table.sort_order_column_name");
        return Objects.nonNull(sortOrder) ? sortOrder : DEFAULT_SORT_ORDER_COLUMN_NAME;
    }

    private String getColumnNameSortBy() {
        String sortBy = (String) getSessionParameter("country_list.table.sort_by_column_name");
        return Objects.nonNull(sortBy) ? sortBy : DEFAULT_SORT_BY_COLUMN_NAME;
    }

    public List<CountryDto> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryDto> countryList) {
        this.countryList = countryList;
    }

    public CountryDto getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(CountryDto selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public DataTable getDataTable() {
        return dataTable;
    }
}
