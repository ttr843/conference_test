package ru.waveaccess.test.conference.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignUpDto {
    @Email(message = "email must be like *****@****.**")
    @NotNull(message = "email can`t be null")
    @NotEmpty(message = "email can`t be empty")
    private String email;
    @NotNull(message = "password can`t be null")
    @NotEmpty(message = "password can`t be empty")
    @Size(min = 8,message = "password must be 8 or more characters")
    private String password;
    @NotNull(message = "first name can`t be null")
    private String firstName;
    @NotNull(message = "last name can`t be null")
    private String lastName;
}
