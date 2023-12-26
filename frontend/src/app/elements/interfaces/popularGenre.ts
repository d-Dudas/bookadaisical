import { Genres } from "src/app/utils/enums/genres";
import { Book } from "../classes/book";

export interface PopularGenre
{
    genre: Genres;
    book: Book;
}
