package com.product.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GenericResponse extends BaseResponse {

    @JsonProperty("total")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    @JsonProperty("errorMessage")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ErrorMessage;
    @JsonProperty("data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonProperty("totalPages")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    @JsonProperty("pageNum")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNum;

    public GenericResponse() {
        total = 0L;
        data = null;
        totalPages = null;
        pageNum = null;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }


    @Override
    public String toString() {
        return "GenericResponse{" +
                "total=" + total +
                ", data=" + data +
                ", totalPages=" + totalPages +
                ", pageNum=" + pageNum +
                '}';
    }
}

