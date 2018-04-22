package app.retake.services.api;

import app.retake.domain.models.Vet;

public interface VetService extends Creatable {

    Vet findByName(final String name);
}
