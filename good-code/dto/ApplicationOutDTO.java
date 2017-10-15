package com.test.web.business.dto;

import com.test.web.business.enums.ApplicationStatus;

import lombok.Data;

@Data
public class ApplicationOutDTO
{
    private Integer id;

    private String emailId;

    private String description;

    private ApplicationStatus status;
}
