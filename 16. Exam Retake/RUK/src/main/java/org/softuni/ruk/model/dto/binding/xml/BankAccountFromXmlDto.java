package org.softuni.ruk.model.dto.binding.xml;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "bank-account")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountFromXmlDto implements Serializable {

    @NotNull
    @Length(min = 1)
    @XmlAttribute(name = "client")
    private String clientFullName;

    @NotNull
    @Length(min = 1)
    @XmlElement(name = "account-number")
    private String accountNumber;

    @XmlElement(name = "balance")
    private BigDecimal balance;
}
