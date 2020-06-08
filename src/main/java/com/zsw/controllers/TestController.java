package com.zsw.controllers;

import com.zsw.controller.BaseController;
import com.zsw.utils.DictionaryStaticURLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangshaowei on 2020/4/2.
 */
@RestController
@RequestMapping(DictionaryStaticURLUtil.testControler)
public class TestController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);










    @Override
    public Logger getLOG(){
        return this.LOG;
    }

}
