package com.test.web.business.service;

import java.util.List;

import com.test.web.business.dto.ApplicationInDTO;
import com.test.web.business.dto.ApplicationOutDTO;
import com.test.web.business.enums.ApplicationStatus;

public interface ApplicationService {

    public List<ApplicationOutDTO> findAll();

    public ApplicationOutDTO findOne(Integer id);

    public ApplicationOutDTO save(ApplicationInDTO applicationInDTO);

    public ApplicationOutDTO update(Integer id, ApplicationInDTO applicationInDTO);

    public void delete(Integer id);
}
