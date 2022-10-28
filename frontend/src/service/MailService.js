import axios from 'axios';
import {
    BASE_URL,
    BULK_EMAIL
} from "../api_config";

const bulkEmail = (subject, content) => {
    return axios.post(BASE_URL + BULK_EMAIL , {
        subject: subject,
        content: content,
    }, {
        headers: {Authorization: 'Bearer ' + localStorage.getItem('login_token')}
    })
}

export const MailService = {
    bulkEmail
}

