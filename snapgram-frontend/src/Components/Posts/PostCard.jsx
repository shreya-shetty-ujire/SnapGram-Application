import React, { useEffect, useState } from 'react'
import { BsThreeDots } from 'react-icons/bs'
import './PostCard.css'
import { AiFillHeart } from 'react-icons/ai';
import { FaRegComment } from 'react-icons/fa';
import { AiOutlineHeart } from 'react-icons/ai';
import { RiSendPlaneLine } from 'react-icons/ri';
import { BsBookmark } from 'react-icons/bs';
import { BsBookmarkFill } from 'react-icons/bs';
import CommentModel from '../Comment/CommentModel';
import { useDisclosure } from '@chakra-ui/react';
import { useDispatch, useSelector } from 'react-redux';
import { likePostAction, savePostAction, unlikePostAction, unsavePostAction } from '../../Redux/Post/Action';
import { isPostLikedByUser, isSavedPost } from '../Config/Utils';
import { useNavigate } from 'react-router-dom';

const PostCard = ({ post }) => {
    const [showDropDown, setShowDropDown] = useState(false);
    const [isPostLike, setIsPostLike] = useState(false);
    const [isSaved, setIsSaved] = useState(false);
    const { isOpen, onOpen, onClose } = useDisclosure();
    const dispatch = useDispatch();

    const { user } = useSelector((store) => store);
    const token = localStorage.getItem("jwtToken");

    const data = { jwt: token, postId: post?.postId }
    const navigate=useNavigate();
    const handleClick = () => {
        setShowDropDown(!showDropDown);
    }
    const handlePostLike = () => {
        setIsPostLike(true);
        dispatch(likePostAction(data))
    }
    const handlePostUnLike = () => {
        setIsPostLike(false);
        dispatch(unlikePostAction(data))
    }
    const handleSavePost = () => {
        setIsSaved(true);
        dispatch(savePostAction(data))

    }
    const handleUnSavePost = () => {
        setIsSaved(false);
        dispatch(unsavePostAction(data))
    }
    const handleOpenCommentModel = () => {
        navigate(`/comment/${post.postId}`)
        onOpen();
    }

    useEffect(() => {
        setIsPostLike(isPostLikedByUser(post, user.reqUser?.userId))
        setIsSaved(isSavedPost(user?.reqUser,post.postId))

    }, [post.likes, user?.reqUser])
console.log("uuuu", user?.reqUser);
    return (
        <div>
            <div className='rounded-md border-b'>
                <div className='flex justify-between items-center py-4'>
                    <div className='flex items-center'>
                        <img className='h-12 w-12 rounded-full' src={user?.reqUser.userImage || "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"} alt='' />
                        <div className='pl-2'>
                            <p className='font-semibold text-sm'>{post?.user.username}</p>
                            <p className='font-thin text-sm'>{post?.location}</p>
                        </div>
                    </div>
                    <div className='dropdown text-2xl'>
                        <BsThreeDots className='dots' onClick={handleClick} />

                        <div className="dropdown-content">
                            {showDropDown && <p className="bg-black text-white py-1 px-4 rounded-md cursor-pointer">
                                Delete</p>}
                        </div>
                    </div>
                </div>
                <div className='w-full '>
                    <img className='post-img' src={post?.image} alt="" />
                </div>

                <div className='flex justify-between items-center w-full py-4'>
                    <div className="flex items-center space-x-6">
                        {isPostLike ? (
                            <AiFillHeart
                                className="text-3xl cursor-pointer hover:opacity-50 text-red-600"
                                onClick={handlePostUnLike}
                            />
                        ) : (
                            <AiOutlineHeart
                                className="text-3xl cursor-pointer hover:opacity-50 text-black" // Ensure it's black when unliked
                                onClick={handlePostLike}
                            />
                        )}


                        <FaRegComment 
                        onClick={handleOpenCommentModel} 
                        className=" text-2xl cursor-pointer hover:opacity-50" />
                        <RiSendPlaneLine className=" text-2xl cursor-pointer hover:opacity-50" />

                    </div>
                    <div className='text-2xl cursor-pointer hover:opacity-50'>
                        {isSaved ? <BsBookmarkFill className='text-2xl cursor-pointer hover:opacity-50'
                            onClick={handleUnSavePost} /> :
                            <BsBookmark onClick={handleSavePost} />}
                    </div>
                </div>
                <div className='pl-0.5'>
                    <div>

                        {post.likes?.length > 0 && <p className='font-semibold'>  {post.likes?.length} likes </p>}

                    </div>
                    <div className=' flex space-x-2'>
                        <p className='font-semibold'>{post?.user.username} </p>
                        <p>{post?.caption}</p>

                    </div>
                    <div>
                        {post.comments?.length > 0 && <p className='text-gray-500'>View all {post.comments?.length} comment </p>}
                    </div>
                    <div className='border-b pb-2 mt-4'>
                        <div className='w-full'>
                            <input className='commentInput' type='text' placeholder='Add a comment..' />
                        </div>
                    </div>

                </div>
            </div>
            <CommentModel
                onClose={onClose}
                isOpen={isOpen}
                isPostLike={isPostLike}
                isSaved={isSaved}
                handlePostClick={handlePostLike}
                handleIsSaved={handleSavePost}
            />
        </div>
    );
};

export default PostCard
