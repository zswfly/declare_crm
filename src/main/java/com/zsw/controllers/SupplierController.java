package com.zsw.controllers;

import com.zsw.controller.BaseController;
import com.zsw.utils.CrmStaticURLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangshaowei on 2020/4/2.
 */
@RestController
@RequestMapping(CrmStaticURLUtil.supplierControler)
public class SupplierController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);










    @Override
    public Logger getLOG(){
        return this.LOG;
    }

}
