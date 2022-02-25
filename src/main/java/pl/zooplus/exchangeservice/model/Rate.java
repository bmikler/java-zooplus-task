package pl.zooplus.exchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {

    private String no;
    private LocalDate effectiveDate;
    private double mid;
}
