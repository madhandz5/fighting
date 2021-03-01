package ryan.fighting.module.domain.product;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQ_PRODUCT")
    private Long id;

    private int prevPrice;
    private int currentPrice;

    @ElementCollection
    private List<Integer> priceList = new ArrayList<>();
}
