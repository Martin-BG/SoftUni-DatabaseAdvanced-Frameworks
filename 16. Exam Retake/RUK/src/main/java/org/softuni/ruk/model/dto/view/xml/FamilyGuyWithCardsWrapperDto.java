package org.softuni.ruk.model.dto.view.xml;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "family-guy")
@XmlAccessorType(XmlAccessType.FIELD)
public class FamilyGuyWithCardsWrapperDto implements Serializable {

    @XmlAttribute(name = "full-name")
    private String fullName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "bank-account")
    private BankAccountWithCardsDto bankAccountDto;
}
