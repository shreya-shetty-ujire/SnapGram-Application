import { FOLLOW_USER, GET_USER_BY_USERNAME, GET_USERS_BY_USER_IDS, REQ_USER, SEARCH_USER, UNFOLLOW_USER, UPDATE_USER } from "./ActionType";

const BASE_API = "http://localhost:8080"

export const getUserProfileAction = (token) => async (dispatch) => {
  
  try {
    const res = await fetch(`${BASE_API}/user/reqProfile`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer "+token
      }
    })

    const reqUser = await res.json();
    console.log("Response from profile API:", reqUser);
    dispatch({ type: REQ_USER, payload: reqUser });
  } catch(error){
    console.log("Catch error", error)
  }
}

export const findUserByUsernameAction = (data) => async (dispatch) => {

  const res = await fetch(`${BASE_API}/user/get/${data.username}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + data.jwt
    }
  })

  const user = await res.json();
  console.log("Find by username: ", user)
  dispatch({ type: GET_USER_BY_USERNAME, payload: user });
}

export const findUserByUserIdsAction = (data) => async (dispatch) => {

  const res = await fetch(`${BASE_API}/user/findByIds/${data.userIds}`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + data.jwt
    }
  })

  const users = await res.json();
  console.log("Find by userIds: ", users)
  dispatch({ type: GET_USERS_BY_USER_IDS, payload: users });

}

export const followUserAction = (data) => async (dispatch) => {

  const res = await fetch(`${BASE_API}/user/follow/${data.userId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + data.jwt
    }
  })

  const user = await res.json();
  console.log("Follow User: ", user)
  dispatch({ type: FOLLOW_USER, payload: user });

}

export const unfollowUserAction = (data) => async (dispatch) => {

  const res = await fetch(`${BASE_API}/user/unfollow/${data.userId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + data.jwt
    }
  })

  const user = await res.json();
  console.log("Unfollow User: ", user)
  dispatch({ type: UNFOLLOW_USER, payload: user });

}

export const searchUserAction = (data) => async (dispatch) => {

  try {
    const res = await fetch(`${BASE_API}/user/search?q=${data.query}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + data.jwt
      }
    })

    const user = await res.json();
    console.log("Search User: ", user)
    dispatch({ type: SEARCH_USER, payload: user });
  } catch(error){
    console.log("Catch error", error)
  }

}

export const editUserAction = (data) => async (dispatch) => {

  try {
    const res = await fetch(`${BASE_API}/user/edit`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + data.jwt
      },
      body: JSON.stringify(data.data)
    })

    const user = await res.json();
    console.log("Update User: ", user)
    dispatch({ type: UPDATE_USER, payload: user });

  } catch(error){
    console.log("Catch error", error)
  }

}