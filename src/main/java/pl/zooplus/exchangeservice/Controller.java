package pl.zooplus.exchangeservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.zooplus.exchangeservice.model.CurrencyExchangeQuotation;


@RestController
public class Controller {

    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    @GetMapping("/api/exchange-rates/{currencyCode}")
    public CurrencyExchangeQuotation getCurrencyRate(@PathVariable String currencyCode) {
        try{
            return service.getLastFiveDaysCurrencyRates(currencyCode);
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Currency code not found");
        }
    }

    @GetMapping("/api/gold-price/average")
    public double getGoldPrice() {

        try{
            return service.getLastTwoWeeksGoldPrice();
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API not found");
        }

    }


}
