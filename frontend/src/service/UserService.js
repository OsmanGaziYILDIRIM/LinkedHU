import axios from "axios"
import {
    BASE_URL,
    PROFILE_VIEW,
    PROFILE_SETTINGS_VIEW,
    PROFILE_SETTINGS_UPDATE,
    BLOCK_USER,
    UNBLOCK_USER,
} from "../api_config"

const getUserById = (userId) => {
    return axios.get(BASE_URL + PROFILE_VIEW + '/' + userId,
        {
            headers: { Authorization: `Bearer ${localStorage.getItem('login_token')}` }
        }
        )
}

const getAllUsers = () => {
    return axios.get(BASE_URL + PROFILE_VIEW)
}

const updateUser = (user) => {
    return axios.post(BASE_URL + PROFILE_SETTINGS_UPDATE,
        {
            userId: user.userId,
            name: user.name,
            surname: user.surname,
            birthdate: user.birthdate,
            gender: user.gender,
            phoneNumber: user.phoneNumber,
            department: user.department,
            graduate_year: user.graduate_year,
            degree: user.degree,
            text: user.text
        },
        {
            headers: { Authorization: `Bearer ${localStorage.getItem('login_token')}` }
        })
}

// Logged-in user can block other users by their userId
const blockUser = (userId) => {
    return axios.post(BASE_URL + BLOCK_USER + '/' + userId)
}
// Logged-in user can unblock other users by their userId
const unblockUser = (userId) => {
    return axios.post(BASE_URL + UNBLOCK_USER + '/' + userId)
}

export const UserService = {
    getUserById,
    getAllUsers,
    updateUser,
    blockUser,
    unblockUser,
}



