package app.retake.services.api;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;

public interface ProcedureService extends Creatable {

    ProcedureWrapperXMLExportDTO exportProcedures();
}
