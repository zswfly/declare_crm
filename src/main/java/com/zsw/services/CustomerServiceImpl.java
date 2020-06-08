package com.zsw.services;

import com.zsw.daos.CustomerMapper;
import com.zsw.entitys.CustomerEntity;
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
public class CustomerServiceImpl  implements ICustomerService,Serializable {

    private static final long serialVersionUID = -813757251127997744L;

    @Autowired
    private IDBService dbService;

    @Resource
    private CustomerMapper customerMapper;


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
    public void newCustomer(CustomerEntity customerEntity, Integer currentUserId)throws Exception {
        customerEntity.setId(null);
        customerEntity.setMnemonicCode(PinyinUtils.getFirstSpell(customerEntity.getName()));
        customerEntity.setStatus(CommonStaticWord.Normal_Status_0);
        customerEntity.setCreateUser(currentUserId);
        customerEntity.setCreateTime(new Timestamp(new Date().getTime()));
        customerEntity.setUpdateUser(currentUserId);
        customerEntity.setUpdateTime(new Timestamp(new Date().getTime()));
        this.dbService.save(customerEntity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = { Exception.class })
    public CustomerEntity updateCustomer(CustomerEntity customerEntity, Integer currentUserId) throws Exception{
        if(customerEntity == null
                || customerEntity.getId() == null
                ){
            throw new Exception("参数错误");
        }

        CustomerEntity result = this.dbService.get(CustomerEntity.class,customerEntity.getId());

        if(result == null) throw new Exception("没有该集装箱id");

        BeanUtils.copyProperties(customerEntity,result);

        result.setUpdateUser(currentUserId);
        result.setUpdateTime(new Timestamp(new Date().getTime()));

        return result;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public CustomerEntity getCustomer(CustomerEntity param) throws Exception{
        return this.dbService.get(param);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CustomerEntity> listCustomerEntity(Map<String, Object> paramMap) throws Exception{
        return this.customerMapper.listCustomerEntity(paramMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Integer listCustomerEntityCount(Map<String, Object> paramMap) throws Exception {
        return this.customerMapper.listCustomerEntityCount(paramMap);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public synchronized String checkCustomerExist(CustomerEntity customerEntity) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        if(customerEntity.getId() == null){

            CustomerEntity param = new CustomerEntity();

            param.setName(customerEntity.getName());
            if( this.dbService.get(param) != null
                    ) stringBuilder.append("集装箱名已存在");

        }else{
            CustomerEntity param = new CustomerEntity();
            List<CustomerEntity> resultList = null;
            param.setName(customerEntity.getName());
            resultList = this.dbService.find(param);
            for(CustomerEntity result :resultList){
                result = this.dbService.get(param);
                if( result != null && result.getId() != customerEntity.getId() ) {
                    stringBuilder.append("集装箱名已存在");
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
        this.customerMapper.batchBan(paramMap);

    }
    

}
