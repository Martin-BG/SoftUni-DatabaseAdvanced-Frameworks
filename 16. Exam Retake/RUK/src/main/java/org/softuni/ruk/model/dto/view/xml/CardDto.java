package org.softuni.ruk.model.dto.view.xml;

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
@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardDto implements Serializable {

    @XmlElement(name = "card-number")
    private String cardNumber;

    @XmlElement(name = "status")
    private String status;
}
