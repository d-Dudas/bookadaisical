
import { BookPurpose } from "../../utils/enums/book-purpose";
import { UserSlim } from "./userSlim";

export class Book {
    uniqueId: number | undefined;
    title: string | undefined;
    author: string | undefined;
    uploader: UserSlim | undefined;
    ISBN: string | undefined;
    images: string[] | undefined;
    views: number | undefined;
    purpose: BookPurpose | undefined;
    priceCurrency: number | undefined;
    pricePoints: number | undefined;
    description: Text | undefined;
    created: Date | undefined;
    active: boolean | undefined;
}
