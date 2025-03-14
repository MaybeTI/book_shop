package com.book.shop.dto;

import com.book.shop.validation.FieldMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@FieldMatch(first = "password", second = "repeatPassword", message = "Passwords must match")
@Schema(description = "DTO for user registration")
public class UserRegistrationRequestDto {

    @NotNull(message = "Email cannot be null")
    @Email(message = "Invalid email format")
    @Schema(description = "User's email address", example = "user@example.com", required = true)
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Schema(description = "User's password",
            example = "securePass123", required = true, minLength = 8)
    private String password;

    @NotNull(message = "Repeat password cannot be null")
    @Schema(description = "Password confirmation", example = "securePass123", required = true)
    private String repeatPassword;

    @NotNull(message = "First name cannot be null")
    @Schema(description = "User's first name", example = "John", required = true)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Schema(description = "User's last name", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Shipping address of the user", example = "123 Main St, City, Country")
    private String shippingAddress;
}
