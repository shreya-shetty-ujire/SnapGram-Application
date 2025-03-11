import { CREATE_COMMENT, CREATE_NEW_POST, DELETE_COMMENT, DELETE_POST, GET_POST_COMMENT, GET_SINGLE_POST, GET_USER_POST, LIKE_COMMENT, LIKE_POST, REQ_USER_POST, SAVE_POST, UNLIKE_COMMENT, UNLIKE_POST, UNSAVE_POST } from "./ActionType";



const initialValue = {
    createComment: null,
    postComment: null,
    likeComment:null,
    unlikeComment: null,
    deleteComment:null
};

export const PostReducer = (store = initialValue, { type, payload }) => {
    if (type === CREATE_COMMENT) {
        return { ...store, createComment: payload };
        // return { ...state, isAuthenticated: true, user: action.payload };
    }
    else if (type === GET_POST_COMMENT) {
        return { ...store, postComment: payload };
    }
    else if (type === LIKE_COMMENT) {
        return { ...store, likeComment: payload };
    }
    else if (type === UNLIKE_COMMENT) {
        return { ...store, unlikeComment: payload };
    }
    else if (type === DELETE_COMMENT) {
        return { ...store, deleteComment: payload };
    }

    return store;
};
