package com.company.sample.web.screens.contractattachment;

import com.company.sample.entity.ContractAttachment;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_ContractAttachment.edit")
@UiDescriptor("contract-attachment-edit.xml")
@EditedEntityContainer("contractAttachmentDc")
@LoadDataBeforeShow
public class ContractAttachmentEdit extends StandardEditor<ContractAttachment> {
}