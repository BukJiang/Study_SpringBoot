package com.buk.validator.pojo.entity;

import com.buk.validator.annotation.MyValidationAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TODO: Lombok
 * TODO: Validation
 * TODO: 自定义注解 - 验证器 @MyValidationAnnotation
 * <p>
 * User
 *
 * @author BuK
 * @since 2020/08/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User {

    @NotNull(
            message = "ID不能为空",
            groups = {Update.class}
    )
    @MyValidationAnnotation(values = {"Long", "id"})
    private Long id;

    @NotNull(
            message = "Name不能为空",
            groups = {Insert.class, Update.class}
    )
    @Size(
            min = 0, max = 10, message = "Name不符合Size",
            groups = {Insert.class, Update.class}
    )
    @MyValidationAnnotation(values = {"String", "name"})
    private String name;

    @NotNull
    @MyValidationAnnotation(values = {"Integer", "age"})
    private Integer age;

    @Valid
    @NotNull(
            message = "UserOtherData1不能为空",
            groups = {Insert.class, Update.class}
    )
    private UserOtherData userOtherData1;

    private UserOtherData userOtherData2;

    public interface Insert {
    }

    public interface Update {
    }
}
