package org.example.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fielValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fielValue){
        super(String.format("%s not found with %s: %s",resourceName,fieldName,fielValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fielValue=fielValue;
    }
}
