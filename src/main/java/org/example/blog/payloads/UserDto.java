package org.example.blog.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=6,message ="Username must be min of 6 characters !!")
    private String name;
    @Email(message = "Email F Address is not valid !!")
    private  String email;
    @NotEmpty
    @Size(min=4,max=10,message = "Password must be min of 4 chars and max of 10 chars !!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    private String password;
    @NotEmpty
    private  String about;
}
