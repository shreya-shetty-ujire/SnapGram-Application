import { applyMiddleware, combineReducers, legacy_createStore } from 'redux';
import { thunk } from 'redux-thunk';
import { AuthReducer } from '../Auth/reducer';
import { UserReducer } from '../User/userReducer';
import { PostReducer } from '../Post/reducer';
import { CommentReducer } from '../Comment/Reducer';


const rootReducers = combineReducers({
    auth: AuthReducer,
    user: UserReducer,
    post: PostReducer,
    comment: CommentReducer
});

const store = legacy_createStore(rootReducers, applyMiddleware(thunk));


export { store };
