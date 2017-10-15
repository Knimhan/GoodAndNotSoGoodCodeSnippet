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

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public ApplicationOutDTO findOne(Integer id) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        validateApplicationExists(applicationEntity);
        return beanMapper.map(applicationEntity, ApplicationOutDTO.class);
    }

    @Override
    public List<ApplicationOutDTO> findAll() {
        List<ApplicationEntity> applicationEntities = applicationDAO.findAll();
        return beanMapper.map(applicationEntities, ApplicationOutDTO.class);
    }

    @Override
    public ApplicationOutDTO save(Integer offerId, ApplicationInDTO applicationDTO) {
        ApplicationEntity applicationEntity = beanMapper.map(applicationDTO, ApplicationEntity.class);
        applicationEntity = applicationDAO.save(applicationEntity);
        return beanMapper.map(applicationEntity, ApplicationOutDTO.class);
    }

    @Override
    public ApplicationOutDTO update(Integer id, ApplicationInDTO applicationInDTO) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        beanMapper.map(applicationInDTO, applicationEntity);
        applicationEntity = applicationDAO.save(applicationEntity);
        return beanMapper.map(applicationEntity, ApplicationOutDTO.class);
    }

    @Override
    public void delete(Integer id) {
        ApplicationEntity applicationEntity = applicationDAO.findOne(id);
        validateApplicationExists(applicationEntity);
        applicationDAO.delete(id);
    }

    private void validateApplicationExists(ApplicationEntity applicationEntity) {
        if (applicationEntity == null) {
            throw new NotFoundException(new ErrorMessage(ErrorCodes.APPLICATION_DOES_NOT_EXISTS));
        }
    }

}
