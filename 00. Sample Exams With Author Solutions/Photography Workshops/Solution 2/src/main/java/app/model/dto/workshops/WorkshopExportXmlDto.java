package app.model.dto.workshops;

import app.model.dto.photographers.ParticipantWrapperXmlDto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopExportXmlDto {

    @XmlAttribute
    private String name;

    @XmlElement(name = "participants")
    private ParticipantWrapperXmlDto participants;

    @XmlTransient
    private BigDecimal pricePerParticipant;

    @XmlAttribute(name = "total-profit")
    public String getTotalProfit() {
        return String.valueOf(pricePerParticipant.multiply
                (BigDecimal.valueOf(participants.getCount())).
                multiply(BigDecimal.valueOf(0.8)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParticipantWrapperXmlDto getParticipants() {
        return participants;
    }

    public void setParticipants(ParticipantWrapperXmlDto participants) {
        this.participants = participants;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }
}
