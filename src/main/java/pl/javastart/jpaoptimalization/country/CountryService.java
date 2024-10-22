package pl.javastart.jpaoptimalization.country;

import org.springframework.stereotype.Service;
import pl.javastart.jpaoptimalization.countrylanguage.LanguageWithUsage;

import java.util.*;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public List<CountryWithBiggestCity> findAllCountriesWithBiggestCity() {
        return countryRepository.findAllCountriesWithBiggestCity();
    }

    public Map<String, List<LanguageWithUsage>> findAllCountriesWithLanguages() {
        List<CountryWithLanguage> countriesWithLanguages = countryRepository.findAllCountriesWithLanguages();
        Map<String, List<LanguageWithUsage>> countryWithLanguagesMap = new TreeMap<>();

        for (CountryWithLanguage cl : countriesWithLanguages) {
            String countryName = cl.getCountryName();
            String language = cl.getLanguage();
            Double percentage = cl.getPercentage();

            LanguageWithUsage languageWithUsage = new LanguageWithUsage(language, percentage);

            countryWithLanguagesMap
                    .computeIfAbsent(countryName, k -> new ArrayList<>())
                    .add(languageWithUsage);
        }
        return countryWithLanguagesMap;
    }

    public List<CountryWithCode> findAllCountriesWithCodes() {
        return countryRepository.findAllCountriesWithCodes();
    }
}
