import { applyMiddleware,combineReducers,legacy_createStore} from "redux";
import thunk from "redux-thunk";
import {UserReducer} from "../User/Reducer";




const rootReducers= combineReducers({
    user:UserReducer
})


export const store=legacy_createStore(rootReducers, applyMiddleware(thunk));