package com.zsw.services;

import com.zsw.entitys.SupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshaowei on 2020/6/8.
 */
public interface ISupplierService extends IBaseService{

    void newSupplier(SupplierEntity supplierEntity, Integer currentUserId)throws Exception;

    SupplierEntity updateSupplier(SupplierEntity supplierEntity, Integer currentUserId) throws Exception;

    SupplierEntity getSupplier(SupplierEntity param) throws Exception;

    List<SupplierEntity> listSupplierEntity(Map<String, Object> paramMap) throws Exception;

    Integer listSupplierEntityCount(Map<String, Object> paramMap) throws Exception;

    String checkSupplierExist(SupplierEntity supplierEntity) throws Exception;

    void batchBan(List<Integer> ids, String type, Integer currentUserId) throws Exception;



}
