package app.retake.domain.dto;

import app.retake.Config;
import app.retake.parser.XMLParser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO implements Serializable, Importable {

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    @Valid
    private List<ProcedureAnimalAidXMLImportDTO> services;

    @XmlElement(name = "animal")
    private String passportNumber;

    @XmlElement(name = "vet")
    private String vetName;

    @XmlElement(name = "date")
    @XmlJavaTypeAdapter(XMLParser.DateTimeAdapter.class)
    private Date date;

    @Override
    public String errorMessage() {
        return Config.MESSAGE_ERROR_INVALID_DATA;
    }

    @Override
    public String successMessage() {
        return Config.RECORD_SUCCESSFULLY_IMPORTED;
    }
}
