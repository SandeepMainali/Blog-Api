package org.example.blog.payloads;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@NoArgsConstructor
@Getter
public class CategoryDto {
    private Integer Id;
    @NotBlank
    @Size(min = 4,message = "Min Size of Category Tittle must be 4")
    private String CategoryTittle;
    @NotBlank
    @Size(min = 10,message = "Min Size of Category Tittle must be 10")
    private String CategoryDescription;

}
