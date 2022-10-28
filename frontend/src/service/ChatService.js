import axios from "axios";

import {
    MESSENGER,
    CLOSEST, BASE_URL
} from "../api_config";

const getChatList = () => {
    return axios.get(BASE_URL+MESSENGER+ CLOSEST,{
        headers: { Authorization: `Bearer ${localStorage.getItem('login_token')}`
        }
    },);
};

const getMessage = (userid) => {
    return axios.get(BASE_URL+MESSENGER+'/'+ userid,{
        headers: { Authorization: `Bearer ${localStorage.getItem('login_token')}`
        }

    });
};

export default {getChatList, getMessage};