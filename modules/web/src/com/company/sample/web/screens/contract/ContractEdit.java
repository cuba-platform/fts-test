package com.company.sample.web.screens.contract;

import com.company.sample.entity.Contract;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
@LoadDataBeforeShow
public class ContractEdit extends StandardEditor<Contract> {
}