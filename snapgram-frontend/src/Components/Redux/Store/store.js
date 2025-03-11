// import { applyMiddleware, configureStore, legacy_createStore } from '@reduxjs/toolkit';
import { UserReducer } from '../User/userReducer';
import { PostReducer } from '../Post/reducer';
import { applyMiddleware, combineReducers, legacy_createStore } from 'redux';
import { thunk } from 'redux-thunk';


const rootReducers = combineReducers({
    user: UserReducer,
    post: PostReducer
});

export const store=legacy_createStore(rootReducers,applyMiddleware(thunk)); 
