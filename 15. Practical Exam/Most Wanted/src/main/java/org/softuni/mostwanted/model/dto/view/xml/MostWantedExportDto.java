package org.softuni.mostwanted.model.dto.view.xml;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedExportDto implements Serializable {

    @XmlAttribute
    private String name;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<MostWantedEntryExportDto> entries = new ArrayList<>();
}
