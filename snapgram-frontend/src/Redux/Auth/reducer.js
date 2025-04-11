import { LOGOUT, RESET_SIGNUP, SIGN_IN, SIGN_UP } from "./ActionType";

const initialValue={
    signup:null,
    signin: null,
    isAuthenticated: !!localStorage.getItem("jwtToken"),
}

export const AuthReducer = (store = initialValue, { type, payload }) => {
    if (type === SIGN_IN) {
        return { ...store, signin: payload };
    }
    else if (type === SIGN_UP) {
        return { ...store, signup: payload };
    }else if (type === LOGOUT) {
        localStorage.removeItem("jwtToken"); 
        return {
            ...store,
            signin: null,
            isAuthenticated: false,
        };
    }else if (type === RESET_SIGNUP) {
        return {
            ...store,
            signup: null
          };
    }
    return store;
}