package pl.zooplus.exchangeservice;

import org.springframework.web.client.RestTemplate;
import pl.zooplus.exchangeservice.model.CurrencyExchangeQuotation;
import pl.zooplus.exchangeservice.model.GoldQuotation;

import java.time.LocalDate;
import java.util.Arrays;


@org.springframework.stereotype.Service
public class Service {

    private final RestTemplate restTemplate;

    public Service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CurrencyExchangeQuotation getLastFiveDaysCurrencyRates(String currencyCode) {

        //TODO check only working days
        LocalDate start = LocalDate.now().minusDays(5);
        LocalDate end = LocalDate.now();

        System.out.println(end.getDayOfWeek());


        String url = String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/?format=json)", currencyCode, start, end);

        return restTemplate.getForObject(url, CurrencyExchangeQuotation.class);

    }

    public double getLastTwoWeeksGoldPrice() {

        LocalDate start = LocalDate.now().minusDays(14);
        LocalDate end = LocalDate.now();

        String url = String.format("http://api.nbp.pl/api/cenyzlota/%s/%s/?format=json", start, end);

        GoldQuotation[] quotations = restTemplate.getForObject(url, GoldQuotation[].class);

        return Arrays.stream(quotations)
                .mapToDouble(GoldQuotation::getPrice)
                .average()
                .orElseThrow(NullPointerException::new);

    }

}
