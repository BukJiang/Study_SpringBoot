package com.buk.validator.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO: Lombok
 * TODO: Validation
 * TODO: 自定义注解 - 验证器 @MyValidationAnnotation
 * <p>
 * UserOtherData
 *
 * @author Buk
 * @date 2020/10/23
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserOtherData {

    @NotNull
    private String otherData1;

    @Size(min = 1, max = 10)
    private String otherData2;

    private String otherData3;
}
