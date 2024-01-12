import { ArtisticMovement } from "src/app/elements/enums/artistic-movement";
import { Condition } from "src/app/elements/enums/condition";
import { Genres } from "src/app/elements/enums/genres";
import { TargetAudience } from "src/app/elements/enums/target-audience";
import { TradingOption } from "src/app/elements/enums/trading-option";

export interface Book {
    id: string,
    title: string,
    author: string,
    description: Text,
    yearOfPublication: number,
    artisticMovement: ArtisticMovement,
    targetAudience: TargetAudience,
    condition: Condition,
    isActive: boolean,
    images: string[],
    genres: Genres[],
    tradingOptions: TradingOption[],

    uploaderUsername: string,
    views: number,
    createdOn: Date,
    lastModifiedOn: Date,

    priceCurrency: number,
    pricePoints: number,
}
