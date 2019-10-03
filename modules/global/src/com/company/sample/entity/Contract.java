package com.company.sample.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@NamePattern("%s|number")
@Table(name = "SAMPLE_CONTRACT")
@Entity(name = "sample_Contract")
public class Contract extends StandardEntity {
    private static final long serialVersionUID = -4739812947507878103L;

    @Column(name = "NUMBER_")
    protected String number;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "contract")
    protected List<ContractAttachment> attachments;

    public void setAttachments(List<ContractAttachment> attachments) {
        this.attachments = attachments;
    }

    public List<ContractAttachment> getAttachments() {
        return attachments;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}