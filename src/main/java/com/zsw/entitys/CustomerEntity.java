package com.zsw.entitys;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by zhangshaowei on 2020/6/8.
 */
@Entity
@Table(name = "customer", schema = "crm")
public class CustomerEntity extends IDEntity{
    private Integer id;
    private String name;
    private Timestamp createTime;
    private Integer createUser;
    private Timestamp updateTime;
    private Integer updateUser;
    private Integer status;
    private String mnemonicCode;
    private String crmObjectTypeIds;
    private String contractScanFiles;
    private Date contractPeriodOfValidity;
    private Integer contractTotalAmount;
    private String reimbursementCostItem;
    private String contact;
    private String contactPhone;
    private String contactAddress;
    private Integer isAudited;
    private Integer auditor;
    private Timestamp auditedAt;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", length=11, nullable=false, unique=true, insertable=true, updatable=false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    //@Column(name = "name", nullable = false, length = 80)
    @NotNull
    @Length(max = 80)
    @Column(name = "name", unique = true,  nullable = false, insertable = true, updatable = true, length = 80)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Basic
    //@Column(name = "create_time", nullable = false)
    @Column(name = "create_time", nullable = true, unique = false, insertable = true, updatable = false)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    //@Column(name = "create_user", nullable = false)
//    @NotNull
//    @Min(1L)
    @Column(name = "create_user", length = 11, nullable = true, unique = false, insertable = true, updatable = false)
    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    @Basic
    //@Column(name = "update_time", nullable = false)
    @Column(name = "update_time", nullable = true, unique = false, insertable = true, updatable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    //@Column(name = "update_user", nullable = false)
    @Column(name = "update_user", length = 11, nullable = true, unique = false, insertable = true, updatable = true)
    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    //@Column(name = "status", nullable = true)
    @Length(max = 3)
    @Column(name = "status", unique = false,  nullable = true, insertable = true, updatable = true, length = 3)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Basic
    @Length(max = 255)
    @Column(name = "mnemonic_code", unique = false,  nullable = true, insertable = true, updatable = true, length = 255)
    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String mnemonicCode) {
        this.mnemonicCode = mnemonicCode;
    }



    @Basic
    @Column(name = "crm_object_type_ids", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCrmObjectTypeIds() {
        return crmObjectTypeIds;
    }

    public void setCrmObjectTypeIds(String crmObjectTypeIds) {
        this.crmObjectTypeIds = crmObjectTypeIds;
    }

    @Basic
    @Column(name = "contract_scan_files", nullable = true, insertable = true, updatable = true, length = 100)
    public String getContractScanFiles() {
        return contractScanFiles;
    }

    public void setContractScanFiles(String contractScanFiles) {
        this.contractScanFiles = contractScanFiles;
    }

    @Basic
    @Column(name = "contract_period_of_validity", nullable = true, insertable = true, updatable = true)
    public Date getContractPeriodOfValidity() {
        return contractPeriodOfValidity;
    }

    public void setContractPeriodOfValidity(Date contractPeriodOfValidity) {
        this.contractPeriodOfValidity = contractPeriodOfValidity;
    }

    @Basic
    @Column(name = "contract_total_amount", nullable = true, insertable = true, updatable = true, precision = 2)
    public Integer getContractTotalAmount() {
        return contractTotalAmount;
    }

    public void setContractTotalAmount(Integer contractTotalAmount) {
        this.contractTotalAmount = contractTotalAmount;
    }

    @Basic
    @Column(name = "reimbursement_cost_item", nullable = true, insertable = true, updatable = true, length = 100)
    public String getReimbursementCostItem() {
        return reimbursementCostItem;
    }

    public void setReimbursementCostItem(String reimbursementCostItem) {
        this.reimbursementCostItem = reimbursementCostItem;
    }

    @Basic
    @Column(name = "contact", nullable = false, insertable = true, updatable = true, length = 100)
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "contact_phone", nullable = false, insertable = true, updatable = true, length = 100)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contact_address", nullable = false, insertable = true, updatable = true, length = 100)
    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    @Basic
    @Column(name = "is_audited", nullable = false, insertable = true, updatable = true)
    public Integer getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Integer isAudited) {
        this.isAudited = isAudited;
    }

    @Basic
    @Column(name = "auditor", nullable = true, insertable = true, updatable = true)
    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    @Basic
    @Column(name = "audited_at", nullable = true)
    public Timestamp getAuditedAt() {
        return auditedAt;
    }

    public void setAuditedAt(Timestamp auditedAt) {
        this.auditedAt = auditedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerEntity that = (CustomerEntity) o;

        if (id != that.id) return false;
        if (createUser != that.createUser) return false;
        if (updateUser != that.updateUser) return false;
        if (isAudited != that.isAudited) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (mnemonicCode != null ? !mnemonicCode.equals(that.mnemonicCode) : that.mnemonicCode != null) return false;
        if (crmObjectTypeIds != null ? !crmObjectTypeIds.equals(that.crmObjectTypeIds) : that.crmObjectTypeIds != null)
            return false;
        if (contractScanFiles != null ? !contractScanFiles.equals(that.contractScanFiles) : that.contractScanFiles != null)
            return false;
        if (contractPeriodOfValidity != null ? !contractPeriodOfValidity.equals(that.contractPeriodOfValidity) : that.contractPeriodOfValidity != null)
            return false;
        if (contractTotalAmount != null ? !contractTotalAmount.equals(that.contractTotalAmount) : that.contractTotalAmount != null)
            return false;
        if (reimbursementCostItem != null ? !reimbursementCostItem.equals(that.reimbursementCostItem) : that.reimbursementCostItem != null)
            return false;
        if (contact != null ? !contact.equals(that.contact) : that.contact != null) return false;
        if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) return false;
        if (contactAddress != null ? !contactAddress.equals(that.contactAddress) : that.contactAddress != null)
            return false;
        if (auditor != null ? !auditor.equals(that.auditor) : that.auditor != null) return false;
        if (auditedAt != null ? !auditedAt.equals(that.auditedAt) : that.auditedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + createUser;
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + updateUser;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (mnemonicCode != null ? mnemonicCode.hashCode() : 0);
        result = 31 * result + (crmObjectTypeIds != null ? crmObjectTypeIds.hashCode() : 0);
        result = 31 * result + (contractScanFiles != null ? contractScanFiles.hashCode() : 0);
        result = 31 * result + (contractPeriodOfValidity != null ? contractPeriodOfValidity.hashCode() : 0);
        result = 31 * result + (contractTotalAmount != null ? contractTotalAmount.hashCode() : 0);
        result = 31 * result + (reimbursementCostItem != null ? reimbursementCostItem.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (contactPhone != null ? contactPhone.hashCode() : 0);
        result = 31 * result + (contactAddress != null ? contactAddress.hashCode() : 0);
        result = 31 * result + isAudited;
        result = 31 * result + (auditor != null ? auditor.hashCode() : 0);
        result = 31 * result + (auditedAt != null ? auditedAt.hashCode() : 0);
        return result;
    }
}
