# TASK 8.1

## BookStore
Task 1 (difficulty 5)

Change the program configuration mechanism to configuration using annotations:

It is necessary to allocate a separate module that will perform automatic configuration of the program. An object containing annotated configuration parameters must be submitted to the input. The module must fill in these parameters with values from the configuration file in accordance with the annotations. The annotation that needs to be implemented for this should have the following form:

@ConfigProperty(configFileName, propertyName, type) – it can be placed over any field (elementary value, reference, array or collection) and must ensure the correct configuration of the corresponding element.

All annotation attributes are optional (when using an annotation, you can either specify them or not). By default сonfigFileName attribute should be the name of the default configuration file, propertyName - have the form CLASS_NAME.FIELD_NAME, type - convert the value of the configurable parameter to the current field type, or to String if the type is common;
