package app.service.api;

import app.model.dto.photographers.PhotographerImportJsonDto;
import app.model.entities.Photographer;

import java.util.List;

public interface PhotographersService {
    void create(Photographer photographer);

    void createMany(PhotographerImportJsonDto[] dtos);

    List<Photographer> findAllOrdered();

    List<Photographer> findAllWithSameCameras();
}
