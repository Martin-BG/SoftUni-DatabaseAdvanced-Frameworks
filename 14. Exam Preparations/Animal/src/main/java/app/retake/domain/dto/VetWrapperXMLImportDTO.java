package app.retake.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name = "vets")
@XmlAccessorType(XmlAccessType.FIELD)
public class VetWrapperXMLImportDTO implements Serializable {

    @XmlElement(name = "vet")
    @Valid
    List<VetXMLImportDTO> vets;
}
