package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.stereotype.Service;
import pl.javastart.jpaoptimalization.country.CountryService;
import pl.javastart.jpaoptimalization.country.CountryWithCode;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class CountryLanguageService {

    private final CountryLanguageRepository countryLanguageRepository;
    private final CountryService countryService;

    public CountryLanguageService(CountryLanguageRepository countryLanguageRepository, CountryService countryService) {
        this.countryLanguageRepository = countryLanguageRepository;
        this.countryService = countryService;
    }

    public List<CountryLanguage> findAll() {
        return countryLanguageRepository.findAll();
    }

    public Map<String, List<String>> findAllLanguagesAndCountries() {
        List<CountryLanguageProjection> allLanguagesAndCountries = countryLanguageRepository.findAllLanguagesAndCountries();
        List<CountryWithCode> allCountriesWithCodes = countryService.findAllCountriesWithCodes();

        Map<String, String> countryCodesAndNames = allCountriesWithCodes.stream()
                .collect(Collectors.toMap(CountryWithCode::getCode, CountryWithCode::getName));

        return allLanguagesAndCountries.stream()
                .collect(Collectors.groupingBy(
                        CountryLanguageProjection::getLanguage, TreeMap::new,
                        Collectors.mapping(
                                clp -> countryCodesAndNames.get(clp.getCountryCode()),
                                Collectors.toList()
                        )
                ));
    }
}
