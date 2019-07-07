package com.coolron.pojo.user;/**
 * Created by Administrator on 2018/11/15.
 */

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Auther: xf
 * @Date: 2018/11/01 23:27
 * @Description:
 */
@Data
public class ValidEntity {

    @NotBlank(message = "name不可以为空")
    @Size(min = 2,max = 4,message = "name的长度范围为2-4")
    private String name;

    @NotNull(message = "age 不能为null")
    @Min(value = 0, message = "age 最小值不能小于0")
    private int age;

}
