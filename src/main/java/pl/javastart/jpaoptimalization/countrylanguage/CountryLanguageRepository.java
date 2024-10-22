package pl.javastart.jpaoptimalization.countrylanguage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryLanguageRepository extends JpaRepository<CountryLanguage, CountryLanguageKey> {

    @Query("select cl.language as language, cl.countryCode as countryCode " +
            "from CountryLanguage cl " +
            "order by cl.language")
    public List<CountryLanguageProjection> findAllLanguagesAndCountries();
}
