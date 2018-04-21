package app.retake.services.impl;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.services.api.ProcedureService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {


    @Override
    public void create(ProcedureXMLImportDTO dto) {
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        return null;
    }
}

