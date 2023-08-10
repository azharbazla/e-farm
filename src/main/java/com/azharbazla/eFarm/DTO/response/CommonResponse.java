package com.azharbazla.eFarm.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommonResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
    private PagingResponse paging;
}
