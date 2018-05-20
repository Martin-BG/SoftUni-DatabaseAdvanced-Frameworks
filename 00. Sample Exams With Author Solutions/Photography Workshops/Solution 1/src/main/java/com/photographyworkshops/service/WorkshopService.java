package com.photographyworkshops.service;

import com.photographyworkshops.domain.dto.workshops.WorkshopImportXMLDto;
import com.photographyworkshops.domain.dto.workshops.WorkshopLocationsExportXMLDto;

public interface WorkshopService {

    void create(WorkshopImportXMLDto workshopImportXMLDto);

    WorkshopLocationsExportXMLDto findAllByLocation();
}
