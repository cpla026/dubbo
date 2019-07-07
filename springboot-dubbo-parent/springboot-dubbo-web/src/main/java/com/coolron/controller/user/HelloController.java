package com.coolron.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.haocang.com
 */
@Slf4j
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {

        return "Who is your little fox, and who is your rose?";
    }

}