package com.bookadaisical.dto.requests;

import com.bookadaisical.enums.ArtisticMovement;
import com.bookadaisical.enums.Condition;
import com.bookadaisical.enums.Genre;
import com.bookadaisical.enums.TargetAudience;
import com.bookadaisical.enums.TradingOption;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSearchFiltersDto {
    //implemented GenreBook class and map it to the db table
    private Genre genre;
    //variable in the Book class
    private TargetAudience targetAudience;
    private ArtisticMovement artisticMovement;
    //variable in the Book class
    private Condition condition;
    //TODO implement class
    private TradingOption tradingOption;
    private int yearOfPublicationNotLessThen;
    private int yearOfPublicationNotBiggerThen;
    private String contains;

    public void setArtisticMovement(String artisticMovement) {
        switch (artisticMovement) {
            case "":
                this.artisticMovement = ArtisticMovement.ALL;
                break;
            case "Ancient Literature":
                this.artisticMovement = ArtisticMovement.ANCIENT_LITERATURE;
                break;
            case "Medieval Literature":
                this.artisticMovement = ArtisticMovement.MEDIEVAL_LITERATURE;
                break;
            case "Baroque":
                this.artisticMovement = ArtisticMovement.BAROQUE;
                break;
            case "Humanism":
                this.artisticMovement = ArtisticMovement.HUMANISM;
                break;
            case "Classicism":
                this.artisticMovement = ArtisticMovement.CLASSICISM;
                break;
            case "Enlightenment":
                this.artisticMovement = ArtisticMovement.ENLIGHTENMENT;
                break;
            case "Realism":
                this.artisticMovement = ArtisticMovement.REALISM;
                break;
            case "Naturalism":
                this.artisticMovement = ArtisticMovement.NATURALISM;
                break;
            case "Impressionism":
                this.artisticMovement = ArtisticMovement.IMPRESSIONISM;
                break;
            case "Poporanism":
                this.artisticMovement = ArtisticMovement.POPORANISM;
                break;
            case "Parnassianism":
                this.artisticMovement = ArtisticMovement.PARNASSIANISM;
                break;
            case "Symbolism":
                this.artisticMovement = ArtisticMovement.SYMBOLISM;
                break;
            case "Samanatorism":
                this.artisticMovement = ArtisticMovement.SAMANATORISM;
                break;
            case "Modernism":
                this.artisticMovement = ArtisticMovement.MODERNISM;
                break;
            case "Expressionism":
                this.artisticMovement = ArtisticMovement.EXPRESSIONISM;
                break;
            case "Dadaism":
                this.artisticMovement = ArtisticMovement.DADAISM;
                break;
            case "Integralism":
                this.artisticMovement = ArtisticMovement.INTEGRALISM;
                break;
            case "Constructivism":
                this.artisticMovement = ArtisticMovement.CONSTRUCTIVISM;
                break;
            case "Surrealism":
                this.artisticMovement = ArtisticMovement.SURREALISM;
                break;
            case "Literary Movements After 1947":
                this.artisticMovement = ArtisticMovement.LITERARY_MOVEMENTS_AFTER_1947;
                break;
            case "Proletkult":
                this.artisticMovement = ArtisticMovement.PROLETKULT;
                break;
            case "Socialist Realism":
                this.artisticMovement = ArtisticMovement.SOCIALIST_REALISM;
                break;
            case "Structuralism":
                this.artisticMovement = ArtisticMovement.STRUCTURALISM;
                break;
            case "Theater of the Absurd":
                this.artisticMovement = ArtisticMovement.THEATER_OF_THE_ABSURD;
                break;
            case "Postmodernism":
                this.artisticMovement = ArtisticMovement.POSTMODERNISM;
                break;
            case "Post-structuralism":
                this.artisticMovement = ArtisticMovement.POST_STRUCTURALISM;
                break;
            case "Feminism":
                this.artisticMovement = ArtisticMovement.FEMINISM;
                break;
            case "Literary Eighties":
                this.artisticMovement = ArtisticMovement.LITERARY_EIGHTIES;
                break;
            case "Himerism":
                this.artisticMovement = ArtisticMovement.HIMERISM;
                break;
            case "Fracturism":
                this.artisticMovement = ArtisticMovement.FRACTURISM;
                break;
            case "Deprimisme":
                this.artisticMovement = ArtisticMovement.DEPRIMISME;
                break;
            case "Generation 2000":
                this.artisticMovement = ArtisticMovement.GENERATION_2000;
                break;
            default:
                this.artisticMovement = ArtisticMovement.ALL;
                break;
        }
    }    

    public void setCondition(String condition) {
        switch (condition) {
            case "":
                this.condition = Condition.ALL;
                break;
            case "New":
                this.condition = Condition.NEW;
                break;
            case "Like New":
                this.condition = Condition.LIKE_NEW;
                break;
            case "Very Good":
                this.condition = Condition.VERY_GOOD;
                break;
            case "Good":
                this.condition = Condition.GOOD;
                break;
            case "Acceptable":
                this.condition = Condition.ACCEPTABLE;
                break;
            default:
                this.condition = Condition.ALL;
                break;
        }
    }
    
    public void setGenre(String genre) {
    switch (genre) {
        case "":
            this.genre = Genre.ALL;
            break;
        case "Alternate History":
            this.genre = Genre.ALTERNATE_HISTORY;
            break;
        case "Academic":
            this.genre = Genre.ACADEMIC;
            break;
        case "Adventure":
            this.genre = Genre.ADVENTURE;
            break;
        case "Bibliography":
            this.genre = Genre.BIBLIOGRAPHY;
            break;
        case "Cookbook":
            this.genre = Genre.COOKBOOK;
            break;
        case "Crime":
            this.genre = Genre.CRIME;
            break;
        case "Classics":
            this.genre = Genre.CLASSICS;
            break;
        case "Dark":
            this.genre = Genre.DARK;
            break;
        case "Dystopian":
            this.genre = Genre.DYSTOPIAN;
            break;
        case "Encyclopedic":
            this.genre = Genre.ENCYCLOPEDIC;
            break;
        case "Essay":
            this.genre = Genre.ESSAY;
            break;
        case "Feminist":
            this.genre = Genre.FEMINIST;
            break;
        case "Fantasy":
            this.genre = Genre.FANTASY;
            break;
        case "Gothic":
            this.genre = Genre.GOTHIC;
            break;
        case "Historical":
            this.genre = Genre.HISTORICAL;
            break;
        case "Horror":
            this.genre = Genre.HORROR;
            break;
        case "Inspirational":
            this.genre = Genre.INSPIRATIONAL;
            break;
        case "Journalistic Writing":
            this.genre = Genre.JOURNALISTIC_WRITING;
            break;
        case "Military":
            this.genre = Genre.MILITARY;
            break;
        case "Manga":
            this.genre = Genre.MANGA;
            break;
        case "Mythic":
            this.genre = Genre.MYTHIC;
            break;
        case "Noir":
            this.genre = Genre.NOIR;
            break;
        case "Philosophical":
            this.genre = Genre.PHILOSOPHICAL;
            break;
        case "Psychological":
            this.genre = Genre.PSYCHOLOGICAL;
            break;
        case "Pop Culture":
            this.genre = Genre.POP_CULTURE;
            break;
        case "Poems":
            this.genre = Genre.POEMS;
            break;
        case "Realist":
            this.genre = Genre.REALIST;
            break;
        case "Religious":
            this.genre = Genre.RELIGIOUS;
            break;
        case "Romantic":
            this.genre = Genre.ROMANTIC;
            break;
        case "Satire":
            this.genre = Genre.SATIRE;
            break;
        case "Surreal Comedy":
            this.genre = Genre.SURREAL_COMEDY;
            break;
        case "Sci-Fi":
            this.genre = Genre.SCI_FI;
            break;
        case "Science":
            this.genre = Genre.SCIENCE;
            break;
        case "Self Help":
            this.genre = Genre.SELF_HELP;
            break;
        case "Saga":
            this.genre = Genre.SAGA;
            break;
        case "Tragicomedy":
            this.genre = Genre.TRAGICOMEDY;
            break;
        case "Tragedy":
            this.genre = Genre.TRAGEDY;
            break;
        case "Thriller":
            this.genre = Genre.THRILLER;
            break;
        case "Urban":
            this.genre = Genre.URBAN;
            break;
        case "Utopian":
            this.genre = Genre.UTOPIAN;
            break;
        case "Western":
            this.genre = Genre.WESTERN;
            break;
        default:
            this.genre = Genre.ALL;
            break;
    }
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
                this.targetAudience = TargetAudience.ALL;
                break;
        }
    }

    public void setTradingOption(String tradingOption) {
    switch (tradingOption) {
        case "":
            this.tradingOption = TradingOption.ALL;
            break;
        case "Currency":
            this.tradingOption = TradingOption.CURRENCY;
            break;
        case "Points":
            this.tradingOption = TradingOption.POINTS;
            break;
        case "Swap":
            this.tradingOption = TradingOption.SWAP;
            break;
        default:
            this.tradingOption = TradingOption.ALL;
            break;
    }
}

}
