export const BASE_URL = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
export const AUTH = "/auth";
export const LOGIN = "/auth/login";
export const REGISTER = "/auth/register";
export const UPDATE_PROFILE = "/update-profile";
export const POSTS = "/posts";
export const PROFILE_VIEW = "/users"; // GET /userId
export const PROFILE_SETTINGS_VIEW = "/users/set"; // GET /userId Admin and Profile owner only
export const PROFILE_SETTINGS_UPDATE = "/users/update"; // UPDATE /userId Admin and Profile owner only
export const SEARCH_USER = "/search/user"; // GET ?searchWord=query
export const SEARCH_POST = "/search/post"; // GET ?searchWord=query
export const SEARCH_COMMENT = "/search/comment"; // GET ?searchWord=query
export const GET_MESSAGE_HISTORY = "/users/messenger"; // GET /userId
export const BLOCK_USER = "/users/block"; // POST /userId
export const UNBLOCK_USER = "/users/unblock"; // POST /userId
export const ADMIN_BAN = "/admin/banspecific"; // POST /admin/ban/userid
export const ADMIN_UNBAN = "/admin/unbanspecific"; // POST  /admin/unban/userid
export const ADMIN_EXCEL = "/admin/export/excel"; // GET
export const ADMIN_ENABLE_USER = "/admin/enablespecific"; // POST /admin/enable/{userId}
export const ADMIN_GET_ALL_USERS = "/admin/return/allusers"; // GET
export const ADMIN_GET_ALL_STUDENT_REPRESENTATIVES = "/admin/return/student-representative"; // GET
export const ADMIN_SET_STUDENT_REPRESENTATIVE = "/admin/set/student-representative"; // POST /{userId}
export const ADMIN_DELETE_STUDENT_REPRESENTATIVE = "/admin/delete/student-representative"; // POST /{userId}
export const BULK_EMAIL = "/users/email"; // POST
export const MESSENGER = "/users/messenger";
export const CLOSEST = "/closest";
export const COMMENTS = "/comments";
