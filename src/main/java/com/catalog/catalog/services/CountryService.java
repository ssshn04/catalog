package com.catalog.catalog.services;

import com.catalog.catalog.entities.Country;
import com.catalog.catalog.responses.CountryResponse;
import com.catalog.catalog.repos.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<CountryResponse> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> {
                    CountryResponse response = new CountryResponse();
                    response.setName(country.getName());
                    response.setCountryId(country.getCountryId());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
