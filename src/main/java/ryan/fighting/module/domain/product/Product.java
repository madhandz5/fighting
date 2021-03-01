package ryan.fighting.module.domain.product;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT", allocationSize = 30, initialValue = 1001)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "SEQ_PRODUCT")
    private Long id;

    private String name;

    private String Description;

    @OneToOne(fetch = FetchType.LAZY , orphanRemoval = true)
    @JoinColumn(name = "PRICE_ID")
    private Price price;

}
