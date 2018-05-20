package app.model.dto.workshops;

import app.model.dto.photographers.ParticipantXMLDto;
import app.model.dto.photographers.PhotographerXmlDto;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopImportXmlDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @XmlAttribute
    private String location;

    @XmlAttribute
    private BigDecimal pricePerParticipant;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<ParticipantXMLDto> participants;

    @XmlElement(name = "trainer")
    private PhotographerXmlDto trainer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public List<ParticipantXMLDto> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantXMLDto> participants) {
        this.participants = participants;
    }

    public PhotographerXmlDto getTrainer() {
        return trainer;
    }

    public void setTrainer(PhotographerXmlDto trainer) {
        this.trainer = trainer;
    }
}
