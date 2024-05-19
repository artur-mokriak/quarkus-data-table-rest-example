package com.data_table.rest_example.repository;

import com.data_table.rest_example.entity.Country;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class CountryRepository implements PanacheRepository<Country> {

    public List<Country> findAll(String sortField,
                                 String sortOrder,
                                 int pageIndex,
                                 int pageSize) {
        Objects.requireNonNull(sortField);
        Objects.requireNonNull(sortOrder);
        try {
            if (sortOrder.isEmpty()) {
                sortOrder = "asc";
            }
            Sort sort = Sort.by(sortField,  sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.Ascending : Sort.Direction.Descending);
            return findAll(sort)
                    .page(pageIndex, pageSize)
                    .list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Country> findByLikeCodeAndName(String findCode,
                                           String findName,
                                           String sortField,
                                           String sortOrder,
                                           int pageIndex,
                                           int pageSize) {
        Objects.requireNonNull(findCode);
        Objects.requireNonNull(findName);
        Objects.requireNonNull(sortField);
        Objects.requireNonNull(sortOrder);
        String valueCode = findCode.toLowerCase() + "%";
        String valueName = findName.toLowerCase() + "%";
        Map<String, Object> params = Parameters.with("valueCode", valueCode)
                .and("valueName", valueName)
                .map();
        try {
            Sort sort = Sort.by(sortField,  sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.Ascending : Sort.Direction.Descending);
            return find("(lower(code) like :valueCode) and (lower(name) like :valueName)", params, sort)
                    .page(pageIndex, pageSize)
                    .list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Country> findByLikeCode(String findCode,
                                    String sortField,
                                    String sortOrder,
                                    int pageIndex,
                                    int pageSize) {
        Objects.requireNonNull(findCode);
        Objects.requireNonNull(sortField);
        Objects.requireNonNull(sortOrder);
        String valueCode = findCode.toLowerCase() + "%";
        Map<String, Object> params = Parameters.with("valueCode", valueCode)
                .map();
        try {
            Sort sort = Sort.by(sortField,  sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.Ascending : Sort.Direction.Descending);
            return find("(lower(code) like :valueCode)", sort, params)
                    .page(pageIndex, pageSize)
                    .list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public List<Country> findByLikeName(String findName,
                                    String sortField,
                                    String sortOrder,
                                    int pageIndex,
                                    int pageSize) {

        Objects.requireNonNull(findName);
        Objects.requireNonNull(sortField);
        Objects.requireNonNull(sortOrder);
        String valueName = findName.toLowerCase() + "%";
        Map<String, Object> params = Parameters.with("valueName", valueName)
                .map();
        try {
            Sort sort = Sort.by(sortField,  sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.Ascending : Sort.Direction.Descending);
            return find("(lower(name) like :valueName)", sort, params)
                    .page(pageIndex, pageSize)
                    .list();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
