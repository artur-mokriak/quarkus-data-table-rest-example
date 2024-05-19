package com.data_table.rest_example.dto;

import java.io.Serializable;
import java.util.List;

public class CountryFindListResponseDto implements Serializable {
    private List<CountryDto> list;
    private PaginationDto pagination;

    public CountryFindListResponseDto() {
    }

    public CountryFindListResponseDto(List<CountryDto> list, PaginationDto pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public List<CountryDto> getList() {
        return list;
    }

    public void setList(List<CountryDto> list) {
        this.list = list;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    public void setPagination(PaginationDto pagination) {
        this.pagination = pagination;
    }

    @Override
    public String toString() {
        return "CountryFindListResponseDto{" +
                "list=" + list +
                ", pagination=" + pagination +
                '}';
    }
}
