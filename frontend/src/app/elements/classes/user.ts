export class User
{
    id: number;
    username: string;
    email: string;
    currentPoints: number;
    totalPoints: number;
    specialCurrency: number;

    constructor(
        id: number,
        username: string,
        email: string,
        currentPoints: number,
        totalPoints: number,
        specialCurrency: number
        )
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.currentPoints = currentPoints;
        this.totalPoints = totalPoints;
        this.specialCurrency = specialCurrency;
    }
}