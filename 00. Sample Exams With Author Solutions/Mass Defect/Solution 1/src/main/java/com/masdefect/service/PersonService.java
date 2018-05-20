package com.masdefect.service;

import com.masdefect.domain.dto.json.PersonExportJSONDto;
import com.masdefect.domain.dto.json.PersonImportJSONDto;

import java.util.List;

public interface PersonService {

    void create(PersonImportJSONDto personImportJSONDto);

    List<PersonExportJSONDto> findInnocentPersons();
}
