import React from 'react'
import { AiFillHeart } from 'react-icons/ai'
import { FaComment } from 'react-icons/fa'
import './UserPostCard.css'

const UserPostCard = ({post}) => {
    return (
        <div className='p-1'>
            <div className='post max-h-96'>
                <img className='cursor-pointer' src={post?.image || "https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"}
                    alt="" />
                <div className='overlay'>
                    <div className='overlay-text flex justify-between'>
                        <div>
                        <AiFillHeart /> {post?.likes?.length || 0}
                        </div>
                        <div>
                        <FaComment /> {post?.comments?.length || 0}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UserPostCard
