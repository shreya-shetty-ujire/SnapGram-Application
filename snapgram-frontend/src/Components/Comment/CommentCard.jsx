import React, { useState } from 'react'
import { AiFillHeart } from 'react-icons/ai'
import { AiOutlineHeart } from 'react-icons/ai';


const CommentCard = () => {
    const [isCommentLike, setIsCommentLike] = useState(false);
    
    const handleLiked = () => {
        setIsCommentLike(!isCommentLike);
    }
   
    return (
        
        <div className='pt-8'>
            <div className='flex items-center justify-between'>
                <div className='flex items-center space-x-3'>
                    <img className='h-12 w-12 rounded-full' src='https://thewowstyle.com/wp-content/uploads/2015/01/nature-wallpaper-27.jpg' alt='' />

                    <div>

                        <p>
                            <span className='font-semibold text-lg'>username2</span>
                            <span className='pl-2'>Looking beautiful</span>
                        </p>

                        <div className='flex space-x-4 text-sm opacity-50 font-semibold pt-2'>
                            <span>2 likes</span>
                            <span>Reply</span>
                        </div>
                    </div>
                </div>
                <div className='cursor-pointer'>
                    {isCommentLike ?
                        <AiFillHeart className="text-xl cursor-pointer hover:opacity-50 text-red-600" onClick={handleLiked} /> : <AiOutlineHeart className="text-xl cursor-pointer hover:opacity-50" onClick={handleLiked} />}
                </div>
                
            </div>
        </div>
        
    )
}

export default CommentCard
