package org.softuni.mostwanted.model.dto.binding.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacesImportXmlDto implements Serializable {

    @XmlElement(name = "race")
    private List<RaceImportXmlDto> entries = new ArrayList<>();
}
