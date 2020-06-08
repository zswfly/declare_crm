package com.zsw.daos;


import com.zsw.entitys.SupplierEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface SupplierMapper {
    Integer listSupplierEntityCount(@Param("paramMap") Map<String,Object> paramMap);

    List<SupplierEntity> listSupplierEntity(@Param("paramMap") Map<String,Object> paramMap);

    void batchBan(@Param("paramMap")Map<String, Object> paramMap);

}