package org.yascode.interaction_plsql.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COUNTRIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {

    @Id
    @Column(name = "COUNTRY_ID")
    @Lob
    private String countryId;

    @Column(name = "COUNTRY_NAME")
    @Lob
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;
}
