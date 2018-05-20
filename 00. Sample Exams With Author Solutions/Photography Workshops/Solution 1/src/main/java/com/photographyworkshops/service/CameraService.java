package com.photographyworkshops.service;

import com.photographyworkshops.domain.dto.cameras.BasicCameraImportJSONDto;

public interface CameraService {

    void create(BasicCameraImportJSONDto basicCameraDto);
}
