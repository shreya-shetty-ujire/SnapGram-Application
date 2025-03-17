import { CREATE_COMMENT, DELETE_COMMENT, GET_POST_COMMENT, LIKE_COMMENT, UNLIKE_COMMENT } from "./ActionType";

const BASE_API = "http://localhost:8080"

export const createCommentAction = (data) => async (dispatch) => {
    
    try {
        const res = await fetch(`${BASE_API}/comments/create/${data.postId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            },
            body: JSON.stringify(data.data)
        })

        const comment = await res.json();
        console.log("Comment Created ", comment)
        dispatch({ type: CREATE_COMMENT, payload: comment })
    } catch (error) {
        console.log("Catch: ", error);
    }

}

export const findPostCommentAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/comments/${data.postId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const comment = await res.json();
        console.log("Find Post Comments ", comment)
        dispatch({ type: GET_POST_COMMENT, payload: comment })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const likeCommentAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/comments/like/${data.commentId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const comment = await res.json();
        console.log("Like Comment ", comment)
        dispatch({ type: LIKE_COMMENT, payload: comment })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const unlikeCommentAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/comments/unlike/${data.commentId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const comment = await res.json();
        console.log("Unlike Comment ", comment)
        dispatch({ type: UNLIKE_COMMENT, payload: comment })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const deleteCommentAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/comments/delete/${data.commentId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const comment = await res.json();
        console.log("Delete Comment ", comment)
        dispatch({ type: DELETE_COMMENT, payload: comment })
    } catch (error) {
        console.log("Catch: ", error);
    }
}