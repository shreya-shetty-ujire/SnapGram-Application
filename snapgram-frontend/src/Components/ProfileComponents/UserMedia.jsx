import React, { useState } from 'react'
import { AiOutlineTable, AiOutlineUser } from 'react-icons/ai'
import { BiBookBookmark } from 'react-icons/bi'
import UserPostCard from './UserPostCard';

const UserMedia = () => {
    const [isActive, setIsActive] = useState('POSTS');


    const mediaItems = [
        {
            tab: 'POSTS',
            icon: <AiOutlineTable></AiOutlineTable>,
            active: ""
        },
        {
            tab: 'SAVED',
            icon: <BiBookBookmark></BiBookBookmark>,
            active: ""
        },
        {
            tab: 'TAGGED',
            icon: <AiOutlineUser></AiOutlineUser>,
            active: ""
        }
    ]
    return (
        <div>
        <div className="border-t flex relative space-x-24 ml-[10rem]">
            {mediaItems.map((item) => <div onClick={() => setIsActive(item.tab)} className={`${isActive == item.tab ? "border-t border-black" : "opacity-70"} flex items-center py-2 cursor-pointer text-sm ml-[19rem]`}>

                <p>{item.icon}</p>
                <p className='ml-2 text-lg'>{item.tab}</p>

            </div>
            )}
        </div>
        <div className='relative justify-center mt-4 ml-[10rem] grid grid-cols-3'>
            {isActive === 'POSTS' && [1,1,1].map((item) => <UserPostCard></UserPostCard>)}
        </div>
        </div>
    )
}

export default UserMedia
