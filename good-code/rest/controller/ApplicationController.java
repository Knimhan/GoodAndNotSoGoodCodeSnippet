package com.test.web.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.web.business.dto.ApplicationInDTO;
import com.test.web.business.dto.ApplicationOutDTO;
import com.test.web.business.enums.ApplicationStatus;
import com.test.web.business.service.ApplicationService;

import io.swagger.annotations.Api;


@Api(tags = "application")
@RestController
@RequestMapping(value = "/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<ApplicationOutDTO> findAll() {
        return applicationService.findAll(offerId);
    }

    @GetMapping("/{id}")
    public ApplicationOutDTO findOne(@PathVariable Integer id) {
        return applicationService.findOne(id);
    }

    @PostMapping
    public ApplicationOutDTO save(@Valid @RequestBody(required = true) ApplicationInDTO applicationInDTO) {
        return applicationService.save(applicationInDTO);
    }

    @PutMapping("/{id}")
    public ApplicationOutDTO update(@PathVariable Integer id, ApplicationInDTO applicationInDTO) {
        return applicationService.update(id, applicationInDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        applicationService.delete(id);
    }
}
