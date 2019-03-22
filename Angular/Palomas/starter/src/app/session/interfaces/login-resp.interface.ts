import { UserResponse } from "./user-resp.interface";

export interface LoginResponse {
    token: string;
    user: UserResponse;
}