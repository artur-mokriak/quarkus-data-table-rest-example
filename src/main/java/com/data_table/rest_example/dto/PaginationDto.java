package com.data_table.rest_example.dto;

public class PaginationDto {
    int totalRecords;
    int pageSize;
    int pageIndex;
    int pageCount;

    public PaginationDto() {
    }

    public PaginationDto(int totalRecords, int pageSize, int pageIndex, int pageCount) {
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageCount = pageCount;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "total=" + totalRecords +
                ", pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", pageCount=" + pageCount +
                '}';
    }
}


