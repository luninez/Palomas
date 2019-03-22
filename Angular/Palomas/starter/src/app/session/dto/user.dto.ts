export class UserDto {
    email: String;
    password: String;
    name: String;

    constructor(email: string, password: string, name: string){
        this.email = email;
        this.password = password;
        this.name = name;
    }
}