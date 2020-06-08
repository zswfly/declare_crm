package com.zsw.controllers;

import com.google.gson.Gson;
import com.zsw.controller.BaseController;
import com.zsw.entitys.SupplierEntity;
import com.zsw.entitys.common.ResponseJson;
import com.zsw.entitys.user.CompanyDto;
import com.zsw.services.ISupplierService;
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
 * Created by zhangshaowei on 2020/5/31.
 */
@RestController
@RequestMapping(CrmStaticURLUtil.supplierControler)
public class SupplierController  extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    ISupplierService supplierService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value= CrmStaticURLUtil.supplierControler_newSupplier,
            method= RequestMethod.POST)
    //    @Permission(code = "dectionary.supplierControler.newSupplier",name = "新增客户",description ="新增客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.supplierControler + CrmStaticURLUtil.supplierControler_newSupplier)
    public String newSupplier(SupplierEntity supplierEntity, @RequestHeader("userId") Integer currentUserId) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            String check =this.supplierService.checkSupplierExist(supplierEntity);
            if(StringUtils.isNotBlank(check) && StringUtils.isNotEmpty(check)){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage(check);
            }

            this.supplierService.newSupplier(supplierEntity,currentUserId);

            responseJson.setCode(ResponseCode.Code_200);
            responseJson.setMessage("新增成功");

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }


    @RequestMapping(value=CrmStaticURLUtil.supplierControler_updateSupplier,
            method= RequestMethod.PUT)
    //    @Permission(code = "dectionary.supplierControler.updateSupplier",name = "更新客户",description ="更新客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.supplierControler + CrmStaticURLUtil.supplierControler_updateSupplier)
    public String updateSupplier(SupplierEntity supplierEntity,@RequestHeader("userId") Integer currentUserId) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            String check =this.supplierService.checkSupplierExist(supplierEntity);
            if(StringUtils.isNotBlank(check) && StringUtils.isNotEmpty(check)){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage(check);
            }

            this.supplierService.updateSupplier(supplierEntity,currentUserId);

            responseJson.setCode(ResponseCode.Code_200);
            responseJson.setMessage("更新成功");

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }




    @RequestMapping(value=CrmStaticURLUtil.supplierControler_getSupplier+"/{supplierId}",
            method= RequestMethod.GET)
    //    @Permission(code = "dectionary.supplierControler.getSupplier",name = "获取单个客户",description ="获取单个客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.supplierControler + CrmStaticURLUtil.supplierControler_getSupplier)
    public String getSupplier(@PathVariable Integer supplierId) throws Exception {
        try {

            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();

            SupplierEntity supplierEntity = new SupplierEntity();
            supplierEntity.setId(supplierId);
            supplierEntity = this.supplierService.getSupplier(supplierEntity);
            if(supplierEntity == null){
                responseJson.setCode(ResponseCode.Code_Bussiness_Error);
                responseJson.setMessage("该id没供应商类型");
            }else{
                responseJson.setCode(ResponseCode.Code_200);
                responseJson.setData(supplierEntity);
            }

            return gson.toJson(responseJson);
        }catch (Exception e){
            CommonUtils.ErrorAction(LOG,e);
            return CommonUtils.ErrorResposeJson(null);
        }
    }

    @RequestMapping(value=CrmStaticURLUtil.supplierControler_supplierPage,
            method= RequestMethod.GET)
    //    @Permission(code = "dectionary.supplierControler.supplierPage",name = "搜索客户",description ="搜索客户"
//            ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.supplierControler + CrmStaticURLUtil.supplierControler_supplierPage)
    public String supplierPage(NativeWebRequest request) throws Exception {
        try {
            ResponseJson responseJson = new ResponseJson();
            Gson gson = CommonUtils.getGson();
            Map<String,Object> paramMap = new HashMap<String, Object>();

            String status = request.getParameter("status");
            if(status !=null && StringUtils.isNotEmpty(status)) {
                paramMap.put("status", Integer.valueOf(NumberUtils.toInt(status, CommonStaticWord.Normal_Status_0)));
            }
            String supplierName = request.getParameter("supplierName");
            if(supplierName !=null && StringUtils.isNotEmpty(supplierName)) {
                paramMap.put("supplierName", supplierName);
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
            List<SupplierEntity> items = this.supplierService.listSupplierEntity(paramMap);
            Integer total = this.supplierService.listSupplierEntityCount(paramMap);
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

    @RequestMapping(value=CrmStaticURLUtil.supplierControler_batchBan,
            method= RequestMethod.PUT)
    //@Permission(code = "dectionary.supplierControler.batchBan",name = "批量禁用/恢复客户",description ="批量禁用/恢复客户"
    //    ,url=CommonStaticWord.dictionaryServices + CrmStaticURLUtil.supplierControler + UserStaticURLUtil.supplierControler_batchBan)
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
                this.supplierService.batchBan(list,type,currentUserId);
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
