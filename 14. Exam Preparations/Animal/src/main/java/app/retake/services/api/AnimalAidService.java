package app.retake.services.api;

import app.retake.domain.models.AnimalAid;

public interface AnimalAidService extends Creatable {

    AnimalAid findByName(String name);
}
