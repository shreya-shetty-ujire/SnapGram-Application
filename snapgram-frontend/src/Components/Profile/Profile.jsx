import React, { useEffect } from 'react'
import UserData from '../ProfileComponents/UserData'
import UserMedia from '../ProfileComponents/UserMedia'
import { useDispatch, useSelector } from 'react-redux'
import { useParams } from 'react-router-dom'
import { findUserByUsernameAction, getUserProfileAction } from '../../Redux/User/userActions'
import { isFollowing, isReqUser } from '../Config/Utils'

const Profile = () => {
    const dispatch= useDispatch();
    const token=localStorage.getItem("jwtToken");
    const {username} = useParams();
    const {user} = useSelector((store) => store);

    const isRequser=isReqUser(user.reqUser?.userId, user.findByUsername?.userId);
    const isFollowed=isFollowing(user.reqUser, user.findByUsername);
    useEffect(() => {
        if (username) { 
            const data = { jwt: token, username };
            dispatch(getUserProfileAction(token, username));
            dispatch(findUserByUsernameAction(data));
        }
    }, [username, user.follower, user.following]); // Keep dependency minimal
    

    return (
        <div className="px-20 w-full pr-80">
            <div>
                <UserData user={isRequser ? user.reqUser: user.findByUsername} isFollowing={isFollowed} isReqUser={isRequser}/>
            </div>
            <div>
                <UserMedia user={isRequser ? user.reqUser: user.findByUsername}/>
            </div>
        </div>

    )
}

export default Profile
