import { applyMiddleware, combineReducers, legacy_createStore } from 'redux';
import { thunk } from 'redux-thunk';
import { AuthReducer } from '../Auth/reducer';
import { UserReducer } from '../User/userReducer';
import { PostReducer } from '../Post/reducer';
import { CommentReducer } from '../Comment/Reducer';

// Load state from localStorage
const loadState = () => {
    try {
        const serializedState = localStorage.getItem("reduxState");
        return serializedState ? JSON.parse(serializedState) : undefined;
    } catch (err) {
        return undefined;
    }
};

// Save state to localStorage
const saveState = (state) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem("reduxState", serializedState);
    } catch (err) {
        console.log("Error saving state:", err);
    }
};

const rootReducers = combineReducers({
    auth: AuthReducer,
    user: UserReducer,
    post: PostReducer,
    comment: CommentReducer
});

// Load persisted state
const persistedState = loadState();

const store = legacy_createStore(rootReducers, persistedState, applyMiddleware(thunk));

// Subscribe to save state changes
store.subscribe(() => {
    saveState(store.getState());
});

export { store };
