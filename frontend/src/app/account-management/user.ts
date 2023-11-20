export class User
{
    id: number;
    username: string;
    token: string;
    key: string;

    constructor(id: number, username: string, token: string, key: string)
    {
        this.id = id;
        this.username = username;
        this.token = token;
        this.key = key;
    }
}
