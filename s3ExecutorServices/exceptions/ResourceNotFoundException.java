package com.amazons3.amazon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    String FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String FieldValue) {
        super(String.format("Resource %s not found for field %s with value %d",resourceName,fieldName,FieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.FieldValue = FieldValue;
    }

}
