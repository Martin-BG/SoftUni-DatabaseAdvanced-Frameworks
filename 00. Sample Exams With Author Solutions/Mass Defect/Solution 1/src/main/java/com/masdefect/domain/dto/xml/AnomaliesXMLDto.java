package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "anomalies")
@XmlAccessorType(XmlAccessType.FIELD)
public class AnomaliesXMLDto {

    @XmlElement(name = "anomaly")
    List<AnomalyXMLDto> anomalies;

    public AnomaliesXMLDto() {
        this.setAnomalies(new ArrayList<>());
    }

    public List<AnomalyXMLDto> getAnomalies() {
        return this.anomalies;
    }

    public void setAnomalies(List<AnomalyXMLDto> anomalies) {
        this.anomalies = anomalies;
    }

    public void add(AnomalyXMLDto anomalyXMLDto) {
        this.getAnomalies().add(anomalyXMLDto);
    }
}
