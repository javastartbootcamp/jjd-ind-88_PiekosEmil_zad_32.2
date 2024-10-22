package pl.javastart.jpaoptimalization.countrylanguage;

public class LanguageWithUsage {

    private String language;

    private Double percentage;

    public LanguageWithUsage(String language, Double percentage) {
        this.language = language;
        this.percentage = percentage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
