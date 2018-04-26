package org.softuni.mostwanted.model.dto.binding.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportXmlDto implements Serializable {

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    @Valid
    List<RaceEntryIdImportXmlDto> entries = new ArrayList<>();

    @NotNull
    @Min(0)
    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String district;
}
