

const { SIGN_IN, SIGN_UP } = require("./ActionType");

export const signinAction = (data) => async (dispatch) => {
    try {
        const res = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: data.username,
                password: data.password,
            })
        });
       const token= res.headers.get("Authorization");
console.log(token);
            localStorage.setItem("jwtToken", token);  
            dispatch({ type: SIGN_IN, payload: token});
    }
    catch (error) {
        console.log(error)
    }
}

export const signupAction = (data) => async (dispatch) => {
    try {
        const res = await fetch("http://localhost:8080/signup", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        });
        const user = await res.json();
        console.log("Signup user: ",user);
        dispatch({ type: SIGN_UP, payload: user });
    }
    catch (error) {
        console.log(error)
    }
}