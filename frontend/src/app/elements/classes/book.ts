import { ArtisticMovement } from "src/app/utils/enums/artistic-movement";
import { Condition } from "src/app/utils/enums/condition";
import { Genres } from "src/app/utils/enums/genres";
import { TargetAudience } from "src/app/utils/enums/target-audience";
import { TradingOption } from "src/app/utils/enums/trading-option";

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
    tradingOptions: TradingOption,

    uploaderUsername: string,
    views: number,
    createdOn: Date,
    lastModifiedOn: Date,

    priceCurrency: number,
    pricePoints: number,
}
