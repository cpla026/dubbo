package com.coolron.controller.user;

import com.coolron.common.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异常处理
 */
@RestController
@Slf4j
public class ExceptionController {

    @RequestMapping(value = "/exception/{number}")
    public ApiResult exception(@PathVariable int number) {

        int res = 10 / number;
        log.info(">>>>>结果number为: {}", res);
        return ApiResult.ok();

    }
}