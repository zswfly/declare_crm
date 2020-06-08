package com.zsw.daos;

import com.zsw.entitys.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CustomerMapper {

    Integer listCustomerEntityCount(@Param("paramMap") Map<String,Object> paramMap);

    List<CustomerEntity> listCustomerEntity(@Param("paramMap") Map<String,Object> paramMap);

    void batchBan(@Param("paramMap")Map<String, Object> paramMap);

}