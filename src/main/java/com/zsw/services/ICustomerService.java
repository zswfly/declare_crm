package com.zsw.services;

import com.zsw.entitys.CustomerEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangshaowei on 2020/6/8.
 */
public interface ICustomerService extends IBaseService{
    void newCustomer(CustomerEntity customerEntity, Integer currentUserId)throws Exception;

    CustomerEntity updateCustomer(CustomerEntity customerEntity, Integer currentUserId) throws Exception;

    CustomerEntity getCustomer(CustomerEntity param) throws Exception;

    List<CustomerEntity> listCustomerEntity(Map<String, Object> paramMap) throws Exception;

    Integer listCustomerEntityCount(Map<String, Object> paramMap) throws Exception;

    String checkCustomerExist(CustomerEntity customerEntity) throws Exception;

    void batchBan(List<Integer> ids, String type, Integer currentUserId) throws Exception;



}
