package com.zsw.services;

import com.zsw.daos.SupplierMapper;
import com.zsw.entitys.SupplierEntity;
import com.zsw.utils.CommonStaticWord;
import com.zsw.utils.PinyinUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangshaowei on 2020/6/8.
 */
public class SupplierServiceImpl  implements ISupplierService,Serializable {


    private static final long serialVersionUID = 1115720016444535714L;

    @Autowired
    private IDBService dbService;

    @Resource
    private SupplierMapper supplierMapper;



    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
    public void newSupplier(SupplierEntity supplierEntity, Integer currentUserId)throws Exception {
        supplierEntity.setId(null);
        supplierEntity.setMnemonicCode(PinyinUtils.getFirstSpell(supplierEntity.getName()));
        supplierEntity.setStatus(CommonStaticWord.Normal_Status_0);
        supplierEntity.setCreateUser(currentUserId);
        supplierEntity.setCreateTime(new Timestamp(new Date().getTime()));
        supplierEntity.setUpdateUser(currentUserId);
        supplierEntity.setUpdateTime(new Timestamp(new Date().getTime()));
        this.dbService.save(supplierEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
    public SupplierEntity updateSupplier(SupplierEntity supplierEntity, Integer currentUserId) throws Exception{
        if(supplierEntity == null
                || supplierEntity.getId() == null
                ){
            throw new Exception("参数错误");
        }

        SupplierEntity result = this.dbService.get(SupplierEntity.class,supplierEntity.getId());

        if(result == null) throw new Exception("没有该供应商id");

        BeanUtils.copyProperties(supplierEntity,result);

        result.setUpdateUser(currentUserId);
        result.setUpdateTime(new Timestamp(new Date().getTime()));

        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SupplierEntity getSupplier(SupplierEntity param) throws Exception{
        return this.dbService.get(param);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SupplierEntity> listSupplierEntity(Map<String, Object> paramMap) throws Exception{
        return this.supplierMapper.listSupplierEntity(paramMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Integer listSupplierEntityCount(Map<String, Object> paramMap) throws Exception {
        return this.supplierMapper.listSupplierEntityCount(paramMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public synchronized String checkSupplierExist(SupplierEntity supplierEntity) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        if(supplierEntity.getId() == null){

            SupplierEntity param = new SupplierEntity();

            param.setName(supplierEntity.getName());
            if( this.dbService.get(param) != null
                    ) stringBuilder.append("供应商名已存在");

        }else{
            SupplierEntity param = new SupplierEntity();
            List<SupplierEntity> resultList = null;
            param.setName(supplierEntity.getName());
            resultList = this.dbService.find(param);
            for(SupplierEntity result :resultList){
                result = this.dbService.get(param);
                if( result != null && result.getId() != supplierEntity.getId() ) {
                    stringBuilder.append("供应商名已存在");
                    break;
                }
            }
        }

        return stringBuilder.toString();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
    public void batchBan(List<Integer> ids, String type, Integer currentUserId) throws Exception{
        int status = CommonStaticWord.Status_ban.equals(type)?CommonStaticWord.Ban_Status_1:CommonStaticWord.Normal_Status_0 ;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("currentUserId",currentUserId);
        paramMap.put("status",status);
        paramMap.put("ids",ids);
        this.supplierMapper.batchBan(paramMap);

    }
    
    
    
    

}
