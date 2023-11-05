package com.bookadaisical;

public class SearchFilters {
    private String genre;
    private TargetAudience targetAudience;
    private String artistitMovement;
    private String condition;
    private int yearOfPublicationNotLessThen;
    private int yearOfPublicationNotBiggerThen;
    private String contains;

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public TargetAudience getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        switch (targetAudience) {
            case "":
                this.targetAudience = TargetAudience.ALL;
                break;
            case "Children's":
                this.targetAudience = TargetAudience.CHILDREN;
                break;
            case "Young adult":
                this.targetAudience = TargetAudience.YOUNG_ADULTS;
                break;
            case "Adult":
                this.targetAudience = TargetAudience.ADULTS;
                break;

            default:
                this.targetAudience =TargetAudience.ALL;
                break;
        }
    }

    public String getArtistitMovement() {
        return artistitMovement;
    }
    public void setArtistitMovement(String artistitMovement) {
        this.artistitMovement = artistitMovement;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public int getYearOfPublicationNotLessThen() {
        return yearOfPublicationNotLessThen;
    }
    public void setYearOfPublicationNotLessThen(int yearOfPublicationNotLessThen) {
        this.yearOfPublicationNotLessThen = yearOfPublicationNotLessThen;
    }
    public int getYearOfPublicationNotBiggerThen() {
        return yearOfPublicationNotBiggerThen;
    }
    public void setYearOfPublicationNotBiggerThen(int yearOfPublicationNotBiggerThen) {
        this.yearOfPublicationNotBiggerThen = yearOfPublicationNotBiggerThen;
    }
    public String getContains() {
        return contains;
    }
    public void setContains(String contains) {
        this.contains = contains;
    }
}
