package org.softuni.mostwanted.model.dto.view.xml;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedEntryExportDto implements Serializable {

    @XmlElement(name = "finish-time")
    private Double finishTime;

    @XmlElement(name = "car")
    private String car;
}
