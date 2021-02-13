package ryan.fighting.module.domain.address;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@DiscriminatorValue(value = "Paypal")
@Table(name = "TB_ADDRESS_PAYPAL")
public class PaypalAddress extends Address {
    private String localCode;
    private String countryCodeAlpha2;
}
