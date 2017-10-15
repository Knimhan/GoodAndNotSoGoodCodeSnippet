package com.test.web.business.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
public class ApplicationInDTO
{
    private static final String EMAIL_REGX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @NotNull
    @Email(regexp = EMAIL_REGX, message = "not valid")
    private String emailId;

    @NotNull
    private String description;

    private ApplicationStatus status;
}
