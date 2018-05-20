package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "procedures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLWrapperExportDTO {

    @XmlElement(name = "procedure")
    @XmlElementWrapper
    private List<ProcedureXMLExportDTO> procedures;

    public ProcedureXMLWrapperExportDTO() {
        this.procedures = new ArrayList<>();
    }

    public ProcedureXMLWrapperExportDTO(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }

    public List<ProcedureXMLExportDTO> getProcedures() {
        return this.procedures;
    }

    public void setProcedures(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }
}
