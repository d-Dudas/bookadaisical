export class UserSlim
{
    id: number;
    username: string;

    constructor(id: number, username: string, token: string, key: string)
    {
        this.id = id;
        this.username = username;
    }
}
