import { Genres } from "src/app/elements/enums/genres";
import { Book } from "../classes/book";

export interface PopularGenre
{
    genre: Genres;
    book: Book;
}
