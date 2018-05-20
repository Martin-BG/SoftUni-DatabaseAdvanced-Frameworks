package com.photographyworkshops.service;

import com.photographyworkshops.domain.dto.photographers.PhotographerExportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographerImportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographerLandscapeExportJSONDto;
import com.photographyworkshops.domain.dto.photographers.PhotographersCameraExportXML;
import com.photographyworkshops.domain.entities.photographers.Photographer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotographerService {

    int create(PhotographerImportJSONDto photographerDto);

    List<PhotographerExportJSONDto> findAllOrder();

    List<PhotographerLandscapeExportJSONDto> findAllWithLenses();

    PhotographersCameraExportXML findAllWithCameras();
}
