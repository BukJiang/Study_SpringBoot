package com.buk.designpattern.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据传输DTO
 *
 * @author jiangbk
 * @date 2021/3/11
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataDTO {

    private String data;
}
