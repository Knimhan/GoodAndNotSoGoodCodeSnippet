package com.test.web.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.database.dao.ApplicationDAO;
import com.test.database.dao.OfferDAO;
import com.test.database.entity.ApplicationEntity;
import com.test.database.entity.OfferEntity;
import com.test.web.business.common.BeanMapper;
import com.test.web.business.dto.ApplicationInDTO;
import com.test.web.business.dto.ApplicationOutDTO;
import com.test.web.business.enums.ApplicationStatus;
import com.test.web.business.error.BadRequestException;
import com.test.web.business.error.ErrorCodes;
import com.test.web.business.error.ErrorMessage;
import com.test.web.business.error.NotFoundException;
import com.test.web.business.service.ApplicationService;


/*
@Creation Date: 15.10.2018
@Author Kumu
@Modified by: Sama @Date: 18.10.2017 @Changes ....bla bla
@Modified by Myra @Date: 20.10.2017  @Changes ....bla bla
@Modified by Oliver @Date: 22.10.2017 @changes ....bla bla
 */
@Api(tags = "application crud operations")
@RestController
@RequestMapping(value = "/api/application")
public class ApplicationCrud {

    //for saving the application in database
    private ApplicationDAO applicationDAO;

    public ApplicationCrud() {
        this.applicationDAO = new ApplicationDAO();
    }

    //public method for finding all application
    @GetMapping("/all")
    public List<ApplicationOutDTO> findAllApplications() {
        List<ApplicationEntity> applicationEntities = applicationDAO.findAll();

        List<ApplicationOutDTO> applicationOutDTOs = new ArrayList<>();

        //create response obj
        for (ApplicationEntity applicationEntity : applicationEntities) {
            ApplicationOutDTO applicationOutDTO = new ApplicationOutDTO();
            applicationOutDTO.setId(applicationEntity.getId());
            applicationOutDTO.setEmailId(applicationEmailId);
            applicationOutDTO.setDescription(applicationEntity.getDescription());
            applicationOutDTO.setStatus(applicationEntity.getStatus());
            applicationOutDTOs.add(applicationOutDTO);
        }

        return applicationOutDTOs;
    }


    //method for finding one application
    @GetMapping("/one/{id}")
    public ApplicationOutDTO findOneApplication(Integer id) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        checkApplicationAlreadyIsThereInSystem(applicationEntity);

        //create response object
        ApplicationOutDTO applicationOutDTO = new ApplicationOutDTO();
        applicationOutDTO.setId(applicationEntity.getId());
        applicationOutDTO.setEmailId(applicationEmailId);
        applicationOutDTO.setDescription(applicationEntity.getDescription());
        applicationOutDTO.setStatus(applicationEntity.getStatus());

        return applicationOutDTO;
    }


    //method for saving application
    @PostMapping("/save")
    public ApplicationOutDTO saveNewApplication(Integer offerId, ApplicationInDTO applicationDTO) {
        ApplicationEntity applicationEntity = beanMapper.map(applicationDTO, ApplicationEntity.class);
        applicationEntity = applicationDAO.save(applicationEntity);

        //create response object
        ApplicationOutDTO applicationOutDTO = new ApplicationOutDTO();
        applicationOutDTO.setId(applicationEntity.getId());
        applicationOutDTO.setEmailId(applicationEmailId);
        applicationOutDTO.setDescription(applicationEntity.getDescription());
        applicationOutDTO.setStatus(applicationEntity.getStatus());

        return applicationOutDTO;
    }


    //method for updating application
    @PutMapping("/update/{id}")
    public ApplicationOutDTO updateExistingApplication(Integer id, ApplicationInDTO applicationInDTO) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        beanMapper.map(applicationInDTO, applicationEntity);
        applicationEntity = applicationDAO.save(applicationEntity);

        //create response object
        ApplicationOutDTO applicationOutDTO = new ApplicationOutDTO();
        applicationOutDTO.setId(applicationEntity.getId());
        applicationOutDTO.setEmailId(applicationEmailId);
        applicationOutDTO.setDescription(applicationEntity.getDescription());
        applicationOutDTO.setStatus(applicationEntity.getStatus());

        return applicationOutDTO;
    }


    //method for deleting one application
    @DeleteMapping("/delete/{id}")
    public void deleteExistingApplication(Integer id) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        checkApplicationAlreadyIsThereInSystem(applicationEntity);
        applicationDAO.delete(id);
        return void;
    }


    private void checkApplicationAlreadyIsThereInSystem(ApplicationEntity applicationEntity) {
        if (applicationEntity == null) {
            throw new RunTimeException("Oops! Runtime Exception: Application is not present in the system");
        }
    }

}
