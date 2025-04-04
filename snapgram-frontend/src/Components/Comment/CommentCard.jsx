import { isCommentLikedByUser, timeDifference } from '../Config/Utils';
import React, { useState } from 'react'
import { AiFillHeart } from 'react-icons/ai'
import { AiOutlineHeart } from 'react-icons/ai';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { likeCommentAction, unlikeCommentAction } from '../../Redux/Comment/Action';


const CommentCard = ({comment, user}) => {
    const [isCommentLike, setIsCommentLike] = useState(false);
    const dispatch= useDispatch();
    const token= localStorage.getItem("jwtToken")
    
    const data={
        commentId: comment.commentId,
        jwt: token
    }
    const handleLikeComment = () => {
        setIsCommentLike(true);
        dispatch(likeCommentAction(data))
    }
    const handleUnLikeComment = () => {
        setIsCommentLike(false);
        dispatch(unlikeCommentAction(data))
    }
    useEffect(()=>{
        setIsCommentLike(isCommentLikedByUser(comment, user?.reqUser?.userId))
    },[user.reqUser, comment]);
    return (
        
        <div className='pt-7'>
            <div className='flex items-center justify-between'>
                <div className='flex items-center space-x-1'>
                    <img className='h-14 w-14 rounded-full' 
                    src={comment.user.image || "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"} alt='' />

                    <div>

                        <p>
                            <span className='font-semibold text-lg'>{user?.username}</span>
                            <span className='pl-2'>{comment.content}</span>
                        </p>

                        <div className='flex space-x-4 text-sm opacity-50 font-semibold pt-2'>
                        <span>{timeDifference(comment?.createdAt)}</span>
                        {comment.likes?.length>0 && <span>{comment.likes?.length || 0} likes</span>}
                        <span>Delete</span>
                        </div>
                    </div>
                </div>
                <div className='cursor-pointer'>
                    {isCommentLike ?(
                        <AiFillHeart className="text-xl cursor-pointer hover:opacity-50 text-red-600" onClick={handleUnLikeComment} /> 
                    ): (
                    <AiOutlineHeart className="text-xl cursor-pointer hover:opacity-50" onClick={handleLikeComment} />
                )}
                </div>
                
            </div>
        </div>
        
    )
}

export default CommentCard
