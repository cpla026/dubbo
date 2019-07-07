package com.coolron.controller.user;/**
 * Created by Administrator on 2018/11/15.
 */

import com.alibaba.fastjson.JSON;
import com.coolron.common.utils.ApiResult;
import com.coolron.pojo.user.ValidEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @Auther: xf
 * @Date: 2018/11/01 23:35
 * @Description: 测试参数校验
 */
@RestController
@Slf4j
public class TestValidController {

    @PostMapping(value = "/valid")
    public ApiResult valid(@Valid @RequestBody ValidEntity validEntity) {
        log.info(">>>>实体类信息为:>>>>{}", JSON.toJSONString(validEntity));
        return ApiResult.ok(validEntity);
    }
}
