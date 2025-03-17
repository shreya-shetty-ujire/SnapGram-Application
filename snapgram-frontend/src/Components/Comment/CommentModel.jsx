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


const CommentModel = ({ onClose, isOpen, isSaved, isPostLike, handleIsSaved, handlePostClick }) => {

    const [commentContent, setCommentContent] = useState();
    const dispatch = useDispatch();
    const token = localStorage.getItem("jwtToken");
    const { postId } = useParams();
    const { comment, user } = useSelector(store => store)


    useEffect(() => {

        const data = { jwt: token, postId }
        if (postId) {
            dispatch(findPostCommentAction(data))
        }

    }, [comment.createComment, postId])


    return (
        <div>
            <Modal size={"6xl"} onClose={onClose} isOpen={isOpen} isCentered>
                <ModalOverlay />
                <ModalContent>

                    <ModalBody>
                        <div>
                            <div className='flex h-[75vh] w-full'>
                                <div className='w-[70%] flex flex-col justify-center'>
                                    <img className='h-full w-full' src="https://wallpapercave.com/wp/wp3720070.jpg" alt="" />
                                </div>
                                <div className='w-[55%] pl-4 pr-3'>
                                    <div className='flex justify-between items-center py-3 border-b'>
                                        <div className='flex items-center'>
                                            <div>
                                                <img className='h-12 w-12 rounded-full' src='https://thewowstyle.com/wp-content/uploads/2015/01/nature-wallpaper-27.jpg' alt='' />
                                            </div>

                                            <div>
                                                <p className='pt-1 pl-3 font-semibold text-lg'>username</p>
                                            </div>
                                        </div>

                                        <BsThreeDots />
                                    </div>
                                    <hr />
                                    <div className='comment'>
                                        {Array.isArray(comment?.postComment) && comment.postComment.length > 0 ? (
                                            comment.postComment.map((item, index) => (
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
                                                <p className='font-semibold'> 50 likes </p>
                                                <p className='text-gray-500 pb-3'>1 day ago </p>
                                            </div>
                                        </div>
                                        <div className='pl-3 pt-4 space-x-4 flex'>

                                            <BsEmojiSmile className='text-2xl' />
                                            <input className='commentInput' type='text'
                                                placeholder='Add a comment..'
                                                onChange={(e) => setCommentContent(e.target.value)}
                                                onKeyPress={(e) => {
                                                    if (e.key === "Enter") {
                                                        const data = {
                                                            postId, jwt: token,
                                                            data: {
                                                                content: commentContent
                                                            }
                                                        }
                                                        dispatch(createCommentAction(data))
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
