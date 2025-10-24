package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LOCATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @Column(name = "LOCATION_ID")
    private Long locationId;

    @Column(name = "STREET_ADDRESS")
    @Lob
    private String streetAddress;

    @Column(name = "POSTAL_CODE")
    @Lob
    private String postalCode;

    @Column(name = "CITY")
    @Lob
    private String city;

    @Column(name = "STATE_PROVINCE")
    @Lob
    private String stateProvince;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country country;
}
