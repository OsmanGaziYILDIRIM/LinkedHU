import axios from "axios";
import {
    AUTH,
    BASE_URL,
    LOGIN,
    REGISTER,
    UPDATE_PROFILE
} from "../api_config";

const login = (username, pass) => {
    saveUsername(username);
    return axios.post(BASE_URL + LOGIN, {username: username, password: pass})
}

const saveUsername = (username) => {
    localStorage.setItem('username', username);
}
const saveUserId = (userId) => {
    localStorage.setItem('userId', userId);
}

const saveAuthority = (authority) => {
    localStorage.setItem('authority', authority);
}

const getUsername = () => {
    return localStorage.getItem("username")
}

const removeUsername = () => {
    localStorage.removeItem('username');
}

const register = (userData) => {
    return axios.post(BASE_URL + REGISTER, userData)
}

// const loginWithToken = () => {
//     return axios.post(BASE_URL + LOGIN_WITH_TOKEN, {}, headersWithToken())
// }

const logout = () => {
    localStorage.removeItem('login_token');
    localStorage.removeItem("auth_roles");
    localStorage.removeItem("username");// email
    localStorage.removeItem("userId");// id int
    localStorage.removeItem("authority");
};

const saveJwtToken = (token) => {
    localStorage.setItem('login_token', token);
}

const getJwtToken = () => {
    return localStorage.getItem('login_token');
}

const hasJwtToken = () => {
    let jwt = localStorage.getItem('login_token');
    return jwt != null;
}

const saveAuthRoles = (roles) => {
    localStorage.setItem("auth_roles", roles);
}

const getAuthRoles = () => {
    return localStorage.getItem("auth_roles");
}

const isAdmin = () => {
    return "ADMIN" in getAuthRoles();
}

// const resetPassword = (email) => {
//     return axios.post(BASE_URL + AUTH + RESET_PASSWORD, null,{params: {email: email}});
// }
//
// const changePasswordWithToken = (newPassword, token) => {
//     return axios.post(BASE_URL +  AUTH + CHANGE_PASSWORD_WITH_TOKEN, null, {params: {token: token, newPassword: newPassword}});
// }

const hasLoggedIn = () => {
    return hasJwtToken();
};


export const AuthService = {
    login, register, logout, saveJwtToken, getJwtToken, hasJwtToken,
    getUsername, saveUserId, saveAuthority,
    removeUsername,
    isAdmin, saveAuthRoles, getAuthRoles, hasLoggedIn
};