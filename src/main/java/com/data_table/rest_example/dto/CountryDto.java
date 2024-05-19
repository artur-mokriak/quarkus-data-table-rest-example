package com.data_table.rest_example.dto;

import com.data_table.rest_example.entity.Country;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class CountryDto implements Serializable {
    private UUID id;
    private String name;
    private String code;

    public CountryDto() {
    }

    public CountryDto(Country country) {
        Objects.requireNonNull(country);
        this.id = country.getId();
        this.name = country.getName();
        this.code = country.getCode();
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
