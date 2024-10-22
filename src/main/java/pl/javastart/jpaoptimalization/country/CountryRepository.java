package pl.javastart.jpaoptimalization.country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {

    @Query("select c.name as countryName, ci.name as cityName, ci.population as population " +
            "from Country c " +
            "left join c.cities ci " +
            "where ci.population is null OR ci.population = (SELECT MAX(cit.population) FROM City cit WHERE cit.country.code = c.code) " +
            "order by c.name")
    List<CountryWithBiggestCity> findAllCountriesWithBiggestCity();

    @Query ("select c.name as countryName, l.language as language, l.percentage as percentage " +
            "from Country c " +
            "left join c.languages l " +
            "where l.countryCode = c.code " +
            "order by c.name, l.percentage desc")
    List<CountryWithLanguage> findAllCountriesWithLanguages();

    @Query ("select c.name as name, c.code as code " +
            "from Country c " +
            "order by c.name")
    List<CountryWithCode> findAllCountriesWithCodes();

}
