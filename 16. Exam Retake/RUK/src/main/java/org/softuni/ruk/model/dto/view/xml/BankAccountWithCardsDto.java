package org.softuni.ruk.model.dto.view.xml;

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
@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountWithCardsDto implements Serializable {

    @XmlElementWrapper(name = "cards")
    @XmlElement(name = "card")
    private List<CardDto> cards = new ArrayList<>();

    @XmlAttribute(name = "account-number")
    private String accountNumber;
}
