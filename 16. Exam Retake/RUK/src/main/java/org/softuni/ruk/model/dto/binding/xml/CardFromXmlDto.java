package org.softuni.ruk.model.dto.binding.xml;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardFromXmlDto implements Serializable {

    @NotNull
    @Length(min = 1)
    @XmlAttribute(name = "status")
    private String cardStatus;

    @NotNull
    @Length(min = 1)
    @XmlAttribute(name = "account-number")
    private String accountNumber;

    @NotNull
    @Length(min = 1)
    @XmlElement(name = "card-number")
    private String cardNumber;
}
