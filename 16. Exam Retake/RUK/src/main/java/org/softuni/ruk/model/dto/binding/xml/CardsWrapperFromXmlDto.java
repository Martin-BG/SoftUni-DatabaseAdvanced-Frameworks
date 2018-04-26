package org.softuni.ruk.model.dto.binding.xml;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@XmlRootElement(name = "cards")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardsWrapperFromXmlDto implements Serializable {

    @XmlElement(name = "card")
    private List<CardFromXmlDto> cards = new ArrayList<>();


}
