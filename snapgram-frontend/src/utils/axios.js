import axios from 'axios'


const API_URL = "http://localhost:8080/";

const api=axios.create({
baseURL : API_URL,
headers : {
  'Content-Type' : 'application/json',
}
});

api.interceptors.response.use(
  response => response,
  error => {
      if (error.response && error.response.status === 401) {
          console.log("Session expired. Logging out...");
          localStorage.removeItem("token");
          window.location.href = "/login";
      }
      return Promise.reject(error);
  }
);

export default api
