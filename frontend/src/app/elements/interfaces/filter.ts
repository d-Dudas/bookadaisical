export interface Filter {
    genre: string[];
    targetAudience: string;
    artisticMovement: string;
    condition: string;
    contains: string;
    yearOfPublicationNotLessThen: number;
    yearOfPublicationNotBiggerThen: number;
}
