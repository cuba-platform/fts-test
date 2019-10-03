package com.company.sample.web.screens.contract;

import com.company.sample.entity.Contract;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Contract.browse")
@UiDescriptor("contract-browse.xml")
@LookupComponent("contractsTable")
@LoadDataBeforeShow
public class ContractBrowse extends StandardLookup<Contract> {
}