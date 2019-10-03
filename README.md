# FTS Test

The project can be used to test the CUBA [FTS](https://github.com/cuba-platform/fts) add-on.

Use the `TestDataGeneratorMBean` in JMX console to generate sample data (see [TestDataGenerator.java](modules/core/src/com/company/sample/jmx/TestDataGenerator.java)).

## Data Model

Main entities:

* `Book` that has a reference to the file with the e-book content and a reference to the `Author` entity.

* `Contract` that has a collection of `ContactAttachment` entities, each of them has a reference to the file.