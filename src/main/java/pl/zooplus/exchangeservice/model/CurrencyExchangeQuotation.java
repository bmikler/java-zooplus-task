package pl.zooplus.exchangeservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeQuotation {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;

}
