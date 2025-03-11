const { CREATE_NEW_POST, GET_USER_POST, REQ_USER_POST, LIKE_POST, UNLIKE_POST, SAVE_POST, UNSAVE_POST, GET_SINGLE_POST, DELETE_POST } = require("./ActionType");

const BASE_API = "http://localhost:8080"

export const createPostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            },
            body: JSON.stringify(data.data)
        })

        const post = await res.json();
        dispatch({ type: CREATE_NEW_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }

}

export const findUserPostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/following/${data.userIds}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const posts = await res.json();
        console.log("Find Post by user ids: ", posts)
        dispatch({ type: GET_USER_POST, payload: posts })
    } catch (error) {
        console.log("Catch: ", error);
    }

}

export const reqUserPostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/following/${data.userId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Find Post by user id: ", post)
        dispatch({ type: REQ_USER_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }

}

export const likePostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/like/${data.postId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Like Post", post)
        dispatch({ type: LIKE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }

}

export const unlikePostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/unlike/${data.postId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Unlike Post", post)
        dispatch({ type: UNLIKE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const savePostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/save/${data.postId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Save Post", post)
        dispatch({ type: SAVE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }
}
export const unsavePostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/unsave/${data.postId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Unsave Post", post)
        dispatch({ type: UNSAVE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const findPostByIdAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/getPostFromId/${data.postId}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Get Single Post", post)
        dispatch({ type: GET_SINGLE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }
}

export const deletePostAction = (data) => async (dispatch) => {
    try {
        const res = await fetch(`${BASE_API}/posts/delete/${data.postId}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + data.jwt
            }
        })

        const post = await res.json();
        console.log("Deleted Post", post)
        dispatch({ type: DELETE_POST, payload: post })
    } catch (error) {
        console.log("Catch: ", error);
    }
}