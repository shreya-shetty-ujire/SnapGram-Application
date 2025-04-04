import React, { useState } from 'react'
import { Modal, ModalBody, ModalContent, ModalOverlay } from "@chakra-ui/react";
import { BsEmojiSmile, BsThreeDots } from 'react-icons/bs'
import CommentCard from './CommentCard';
import { FaRegComment } from 'react-icons/fa';
import { RiSendPlaneLine } from 'react-icons/ri';
import { BsBookmark } from 'react-icons/bs';
import { BsBookmarkFill } from 'react-icons/bs';
import { AiFillHeart } from 'react-icons/ai'
import { AiOutlineHeart } from 'react-icons/ai';
import './CommentModel.css'
import { useDispatch, useSelector } from 'react-redux';
import { createCommentAction, findPostCommentAction } from '../../Redux/Comment/Action';
import { useParams } from 'react-router-dom';
import { useEffect } from 'react';
import { findPostByIdAction } from '../../Redux/Post/Action';
import { timeDifference } from '../Config/Utils';


const CommentModel = ({ onClose, isOpen, isSaved, isPostLike, handleIsSaved, handlePostClick }) => {

    const [commentContent, setCommentContent] = useState();
    const dispatch = useDispatch();
    const token = localStorage.getItem("jwtToken");
    const { postId } = useParams();
    const { user, comment, post } = useSelector(store => store)


    useEffect(() => {

        const data = { jwt: token, postId }
        if (postId) {
            dispatch(findPostByIdAction(data))
        }

    }, [comment.createComment, postId, comment.likeComment]);
    return (
        <div>
            <Modal size={"6xl"} onClose={onClose} isOpen={isOpen} isCentered>
                <ModalOverlay />
                <ModalContent>

                    <ModalBody>
                        <div>
                            <div className='flex h-[75vh] w-full'>
                                <div className='w-[70%] flex flex-col justify-center'>
                                    <img className='h-full w-full' src={post?.singlePost?.image} alt="" />
                                </div>
                                <div className='w-[55%] pl-4 pr-3'>
                                    <div className='flex justify-between items-center py-3 border-b'>
                                        <div className='flex items-center'>
                                            <div>
                                                <img className='h-14 w-14 rounded-full'
                                                    src={user.reqUser.image || "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"} alt='' />
                                            </div>


                                            <p className='pl-1 font-semibold text-lg'>{user?.reqUser?.username}</p>

                                        </div>

                                        <BsThreeDots />
                                    </div>
                                    <hr />
                                    <div className='comment'>
                                        {post?.singlePost?.comments && post.singlePost.comments.length > 0 ? (
                                            post.singlePost.comments.map((item, index) => (
                                                <CommentCard key={index} comment={item} user={item.user} />
                                            ))
                                        ) : (
                                            <p className="text-gray-500 text-center py-4">No comments yet. Be the first to comment!</p>
                                        )}
                                    </div>

                                    <div className='pt-14'>
                                        <div className='border-b'>
                                            <div className='flex justify-between items-center w-full pt-12 ml-2 pr-1'>
                                                <div className='flex items-center space-x-6'>
                                                    {isPostLike ? <AiFillHeart className="text-3xl cursor-pointer hover:opacity-50 text-red-600" onClick={handlePostClick} /> : <AiOutlineHeart className="text-3xl cursor-pointer hover:opacity-50" onClick={handlePostClick} />}


                                                    <FaRegComment className=" text-2xl cursor-pointer hover:opacity-50" />
                                                    <RiSendPlaneLine className=" text-2xl cursor-pointer hover:opacity-50" />

                                                </div>
                                                <div className='text-2xl cursor-pointer hover:opacity-50'>
                                                    {isSaved ? <BsBookmarkFill className='text-2xl cursor-pointer hover:opacity-50' onClick={handleIsSaved} /> : <BsBookmark onClick={handleIsSaved} />}
                                                </div>

                                            </div>
                                            <div className='pl-3'>
                                                {post.singlePost?.likes.length > 0 && <p className='font-semibold'> {post.singlePost?.likes.length} likes </p>}
                                                <p className='text-gray-500 pb-3'> {timeDifference(post.singlePost?.createdAt)}</p>
                                            </div>
                                        </div>
                                        <div className='pl-3 pt-4 space-x-4 flex'>

                                            <BsEmojiSmile className='text-2xl' />
                                            <input className='commentInput' type='text'
                                                placeholder='Add a comment..'
                                                onChange={(e) => setCommentContent(e.target.value)}
                                                value={commentContent}
                                                onKeyPress={(e) => {
                                                    if (e.key === "Enter") {
                                                        const data = {
                                                            postId: postId, jwt: token,
                                                            data: {
                                                                content: commentContent
                                                            }
                                                        }
                                                        dispatch(createCommentAction(data))
                                                        setCommentContent("")
                                                    }
                                                }}
                                            />

                                        </div>
                                    </div>
                                </div>


                            </div>


                        </div>
                    </ModalBody>
                </ModalContent>
            </Modal>
        </div >
    )
}

export default CommentModel
