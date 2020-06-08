package com.zsw.controllers;

import com.google.gson.Gson;
import com.zsw.controller.BaseController;
import com.zsw.entitys.CustomerEntity;
import com.zsw.entitys.common.ResponseJson;
import com.zsw.services.ICustomerService;
import com.zsw.utils.*;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshaowei on 2020/4/2.
 */
@RestController
@RequestMapping(CrmStaticURLUtil.customerControler)
public class CustomerController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    ICustomerService customerService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value= CrmStaticURLUtil.customerControler_newCustomer,
            method= RequestMethod.POST)
    //    @Permission(code = "dectionary.customerControler.newCustomer",name = "新增客户",description ="新增客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.customerControler + CrmStaticURLUtil.customerControler_newCustomer)
    public String newCustomer(CustomerEntity customerEntity, @RequestHeader("userId") Integer currentUserId) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            String check =this.customerService.checkCustomerExist(customerEntity);
            if(StringUtils.isNotBlank(check) && StringUtils.isNotEmpty(check)){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage(check);
            }

            this.customerService.newCustomer(customerEntity,currentUserId);

            responseJson.setCode(ResponseCode.Code_200);
            responseJson.setMessage("新增成功");

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }


    @RequestMapping(value=CrmStaticURLUtil.customerControler_updateCustomer,
            method= RequestMethod.PUT)
    //    @Permission(code = "dectionary.customerControler.updateCustomer",name = "更新客户",description ="更新客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.customerControler + CrmStaticURLUtil.customerControler_updateCustomer)
    public String updateCustomer(CustomerEntity customerEntity,@RequestHeader("userId") Integer currentUserId) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            String check =this.customerService.checkCustomerExist(customerEntity);
            if(StringUtils.isNotBlank(check) && StringUtils.isNotEmpty(check)){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage(check);
            }

            this.customerService.updateCustomer(customerEntity,currentUserId);

            responseJson.setCode(ResponseCode.Code_200);
            responseJson.setMessage("更新成功");

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }




    @RequestMapping(value=CrmStaticURLUtil.customerControler_getCustomer+"/{customerId}",
            method= RequestMethod.GET)
    //    @Permission(code = "dectionary.customerControler.getCustomer",name = "获取单个客户",description ="获取单个客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.customerControler + CrmStaticURLUtil.customerControler_getCustomer)
    public String getCustomer(@PathVariable Integer customerId) throws Exception {
        try {

            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setId(customerId);
            customerEntity = this.customerService.getCustomer(customerEntity);
            if(customerEntity == null){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage("该id没客户类型");
            }else{
                responseJson.setCode(ResponseCode.Code_200);
                responseJson.setData(customerEntity);
            }

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }

    @RequestMapping(value=CrmStaticURLUtil.customerControler_customerPage,
            method= RequestMethod.GET)
    //    @Permission(code = "dectionary.customerControler.customerPage",name = "搜索客户",description ="搜索客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.customerControler + CrmStaticURLUtil.customerControler_customerPage)
    public String customerPage(NativeWebRequest request) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();
            Map<String,Object> paramMap = new HashMap<String, Object>();

            String status = request.getParameter("status");
            if(status !=null && StringUtils.isNotEmpty(status)) {
                paramMap.put("status", Integer.valueOf(NumberUtils.toInt(status, CommonStaticWord.Normal_Status_0)));
            }
            String customerName = request.getParameter("customerName");
            if(customerName !=null && StringUtils.isNotEmpty(customerName)) {
                paramMap.put("customerName", customerName);
            }

            String mnemonicCode = request.getParameter("mnemonicCode");
            if(mnemonicCode !=null && StringUtils.isNotEmpty(mnemonicCode)) {
                paramMap.put("mnemonicCode", mnemonicCode);
            }

            String beginCreateTime = request.getParameter("beginCreateTime");
            if(beginCreateTime !=null && StringUtils.isNotEmpty(beginCreateTime)) {
                paramMap.put("beginCreateTime", beginCreateTime);
            }
            String endCreateTime = request.getParameter("endCreateTime");
            if(endCreateTime !=null && StringUtils.isNotEmpty(endCreateTime)) {
                paramMap.put("endCreateTime", endCreateTime);
            }


            Integer currentPage = Integer.valueOf(NumberUtils.toInt(request.getParameter("currentPage"), 1));
            Integer pageSize = Integer.valueOf(NumberUtils.toInt(request.getParameter("pageSize"), 10));

            paramMap.put("start", (currentPage-1)*pageSize);
            paramMap.put("pageSize", pageSize);

            Map<String,Object> data = new HashMap<>();
            List<CustomerEntity> items = this.customerService.listCustomerEntity(paramMap);
            Integer total = this.customerService.listCustomerEntityCount(paramMap);
            data.put("items",items);
            data.put("total",total==null?0:total);
            responseJson.setData(data);
            responseJson.setCode(ResponseCode.Code_200);
            responseJson.setMessage("搜索成功");

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }

    @RequestMapping(value=CrmStaticURLUtil.customerControler_batchBan,
            method= RequestMethod.PUT)
    //@Permission(code = "dectionary.customerControler.batchBan",name = "批量禁用/恢复客户",description ="批量禁用/恢复客户"
    //    ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.customerControler + UserStaticURLUtil.customerControler_batchBan)
    public String batchBan( @RequestParam Map<String, String> params , @RequestHeader("userId") Integer currentUserId) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();
            String ids = params.get("ids");
            String type = params.get("type");
            if(ids == null || type == null){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage("参数不全");
                return gson.toJson(responseJson);
            }else{
                List<Integer> list = Arrays.asList(gson.fromJson(ids, Integer[].class));
                this.customerService.batchBan(list,type,currentUserId);
                responseJson.setCode(ResponseCode.Code_200);
                responseJson.setMessage("更新成功");
                return gson.toJson(responseJson);
            }

        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }














    @Override
    public Logger getLOG(){
        return this.LOG;
    }

}
