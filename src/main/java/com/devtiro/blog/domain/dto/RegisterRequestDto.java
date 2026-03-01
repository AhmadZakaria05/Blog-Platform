package com.devtiro.blog.domain.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 32)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
            message = "Password must contain upper, lower case letters and a number"

    )
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    @Size(min = 3, max = 20)
    @Pattern(
            regexp = "^[a-zA-Z0-9_]+$",
            message = "Username may contain only letters, numbers, and underscores"
    )
    private String name;

}
