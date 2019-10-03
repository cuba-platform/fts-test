package com.company.sample.jmx;

import com.haulmont.cuba.core.sys.jmx.JmxBean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

@JmxBean(module = "fts-test", alias = "TestDataGeneratorMBean")
@ManagedResource(description = "Test data generator")
public interface TestDataGeneratorMBean {

    @ManagedOperation(description = "Generate authors")
    String generateAuthors();

    @ManagedOperationParameters({@ManagedOperationParameter(name = "numberOfBooks", description = "Number of books to generate")})
    @ManagedOperation(description = "Generate books")
    String generateBooks(int numberOfBooks);

    @ManagedOperationParameters(
            {
                    @ManagedOperationParameter(name = "numberOfBooks", description = "Number of contracts  to generate"),
                    @ManagedOperationParameter(name = "numberOfAttachments", description = "Number of attachments in each contract")
            }
            )
    @ManagedOperation(description = "Generate contracts with attachments")
    String generateContracts(int numberOfContracts, int numberOfAttachments);
}