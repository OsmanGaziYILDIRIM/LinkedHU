import axios from 'axios';
import {
    BASE_URL,
    SEARCH_USER,
    SEARCH_POST,
    SEARCH_COMMENT
} from "../api_config";

const searchUser = (query) => {
    return axios.get(BASE_URL + SEARCH_USER, {
        params: {
            "searchWord": query
        }
    });
}

const searchPost = (query) => {
    return axios.get(BASE_URL + SEARCH_POST, {
        params: {
            "searchWord": query
        }
    })
};

const searchComment = (query) => {
    return axios.get(BASE_URL + SEARCH_COMMENT, {
        params: {
            "searchWord": query
        }
    })
};

export const SearchService = {
    searchUser,
    searchPost,
    searchComment
}