package com.transportmanagmentsystem.dto;

import com.transportmanagmentsystem.entity.CustomUser;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDTO {

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    @Email(message = "{page.add-driver.form.error.email.wrong-format}")
    private String email;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    @Size(min = 5, max = 30, message = "{page.add-driver.form.error.password.wrong-size}")
    private String password;

    @NotEmpty(message = "{page.add-driver.form.error.empty}")
    private String fullName;

    private CustomUser.CustomUserRole role;
}
