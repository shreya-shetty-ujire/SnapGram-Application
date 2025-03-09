import React, { useState } from 'react'
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

const PostCard = () => {
    const [showDropDown, setShowDropDown] = useState(false);
    const [isPostLike, setIsPostLike] = useState(false);
    const [isSaved, setIsSaved] = useState(false);
    const {isOpen, onOpen, onClose} = useDisclosure();

    const handleClick = () => {
        setShowDropDown(!showDropDown);
    }
    const handlePostClick = () => {
        setIsPostLike(!isPostLike);
    }
    const handleIsSaved = () => {
        setIsSaved(!isSaved);
    }
    const handleOpenCommentModel = () => {
        onOpen();
    }

    return (
        <div>
            <div className='rounded-md'>
                <div className='flex justify-between items-center py-4'>
                    <div className='flex items-center'>
                        <img className='h-12 w-12 rounded-full' src='https://thewowstyle.com/wp-content/uploads/2015/01/nature-wallpaper-27.jpg' alt='' />
                        <div className='pl-2'>
                            <p className='font-semibold text-sm'>username</p>
                            <p className='font-thin text-sm'>Location</p>
                        </div>
                    </div>
                    <div className='dropdown text-2xl'>
                        <BsThreeDots className='dots' onClick={handleClick} />

                        <div className="dropdown-content">
                            {showDropDown && <p className="bg-black text-white py-1 px-4 rounded-md cursor-pointer">Delete</p>}
                        </div>
                    </div>
                </div>
                <div className='w-full '>
                    <img className='post-img' src="https://wallpapercave.com/wp/wp3720070.jpg" alt="" />
                </div>
                <div className='flex justify-between items-center w-full py-4'>
                    <div className='flex items-center space-x-6'>
                        {isPostLike ? <AiFillHeart className="text-3xl cursor-pointer hover:opacity-50 text-red-600" onClick={handlePostClick} /> : <AiOutlineHeart className="text-3xl cursor-pointer hover:opacity-50" onClick={handlePostClick} />}


                        <FaRegComment onClick={handleOpenCommentModel} className=" text-2xl cursor-pointer hover:opacity-50" />
                        <RiSendPlaneLine className=" text-2xl cursor-pointer hover:opacity-50" />

                    </div>
                    <div className='text-2xl cursor-pointer hover:opacity-50'>
                        {isSaved ? <BsBookmarkFill className='text-2xl cursor-pointer hover:opacity-50' onClick={handleIsSaved} /> : <BsBookmark onClick={handleIsSaved} />}
                    </div>
                </div>
                <div className='pl-0.5'>
                    <div>
                        <p className='font-semibold'> 50 likes </p>

                    </div>
                    <div className=' flex space-x-2'>
                        <p className='font-semibold'>username </p>
                        <p>Serene</p>

                    </div>
                    <div>
                        <p className='text-gray-500'>View 10 comment </p>
                    </div>
                    <div className='border-b pb-2'>
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
                handlePostClick={handlePostClick}
                handleIsSaved={handleIsSaved}
            />
        </div>
    );
};

export default PostCard
