import axios from 'axios'
import {
    BASE_URL,
    ADMIN_BAN,
    ADMIN_UNBAN,
    ADMIN_EXCEL,
    ADMIN_ENABLE_USER,
    ADMIN_GET_ALL_USERS,
    ADMIN_GET_ALL_STUDENT_REPRESENTATIVES,
    ADMIN_SET_STUDENT_REPRESENTATIVE,
    ADMIN_DELETE_STUDENT_REPRESENTATIVE,
    PROFILE_SETTINGS_VIEW,
    PROFILE_SETTINGS_UPDATE,
} from '../api_config'

const adminBanUserByUserId = (userId) => { // username means email
    return axios.post(BASE_URL + ADMIN_BAN + '/' + userId,{
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminUnbanUserByUserId = (userId) => {
    return axios.post(BASE_URL + ADMIN_UNBAN + '/' + userId, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminExportExcel = () => {
    return axios.get(BASE_URL + ADMIN_EXCEL, {
        responseType: 'arraybuffer',
        headers: {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + localStorage.getItem('login_token')
        }
    })
}

const adminEnableUserById = (userId) => {
    return axios.post(BASE_URL + ADMIN_ENABLE_USER + '/' + userId, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminGetAllUsers = () => {
    return axios.get(BASE_URL + ADMIN_GET_ALL_USERS, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminGetAllStudentRepresentatives = () => {
    return axios.get(BASE_URL + ADMIN_GET_ALL_STUDENT_REPRESENTATIVES, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminSetStudentRepresentative = (userId) => {
    return axios.post(BASE_URL + ADMIN_SET_STUDENT_REPRESENTATIVE + '/' +userId, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

const adminDeleteStudentRepresentative = (userId) => {
    return axios.post(BASE_URL + ADMIN_DELETE_STUDENT_REPRESENTATIVE + '/' +userId, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

export const AdminService = {
    adminBanUserByUserId,
    adminUnbanUserByUserId,
    adminExportExcel,
    adminEnableUserById,
    adminGetAllUsers,
    adminGetAllStudentRepresentatives,
    adminSetStudentRepresentative,
    adminDeleteStudentRepresentative,

}