package com.kogus.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fundName;
    private String fundId;
    private String fundCode;
    private String fundType;
    private String fundFounder;
    private double fundPrice;
    @Temporal(TemporalType.DATE)
    private Date fundDate;
}
