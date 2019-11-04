package com.wy.producerservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-04-17
 * Description:
 */
@Data
public class TestDto {
    @NotBlank
    private Integer a;
    @NotBlank
    private String b;
}
