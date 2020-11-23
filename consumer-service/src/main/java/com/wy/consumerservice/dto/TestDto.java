package com.wy.consumerservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangtoye
 * @date: 2019-04-17
 * Description:
 */
@Data
@Accessors(chain=true)
public class TestDto {
    @NotBlank
    private Integer a;
    @NotBlank
    private String b;

}
