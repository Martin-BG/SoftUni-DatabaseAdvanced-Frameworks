package com.photographyworkshops.service;

import com.photographyworkshops.domain.dto.lens.LenImportJSONDto;

public interface LenService {

    void create(LenImportJSONDto lenDto);
}
