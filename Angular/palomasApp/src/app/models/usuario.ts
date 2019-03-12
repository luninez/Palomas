export class Usuario {
    email: string;
    username: string;
    photoUrl: string;
    name: string;

    constructor(email: string, username: string, photoUrl: string, name: string) {
        this.email = email;
        this.username = username;
        this.photoUrl = photoUrl;
        this.name = name;
    }
}