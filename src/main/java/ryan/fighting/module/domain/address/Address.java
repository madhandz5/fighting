package ryan.fighting.module.domain.address;


import lombok.*;
import ryan.fighting.module.domain.Account;
import ryan.fighting.module.domain.address.enums.AddressType;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.InheritanceType.JOINED;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = JOINED)
@Table(name = "TB_ADDRESS")
@SequenceGenerator(name = "SEQ_ADDRESS", sequenceName = "SEQ_ADDRESS", initialValue = 1, allocationSize = 30)
public abstract class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADDRESS")
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Account account;

    private String addressName;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private boolean isPreferred;
    private String firstName;
    private String lastName;
    private String street1;
    private String street2;
    private String city;
    private String country;
    private String zipcode;
    private String phone;
    private LocalDateTime createDate;
    private LocalDateTime updateTime;

}
