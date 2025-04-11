import React, { useEffect, useState } from 'react'
import { AiOutlineTable, AiOutlineUser } from 'react-icons/ai'
import { BiBookBookmark } from 'react-icons/bi'
import UserPostCard from './UserPostCard';
import { useDispatch, useSelector } from 'react-redux';
import { reqUserPostAction } from '../../Redux/Post/Action';

const UserMedia = ({ user }) => {
    const [isActive, setIsActive] = useState('POSTS');
    const dispatch = useDispatch();
    const token = localStorage.getItem("jwtToken");
    const { post } = useSelector(store => store);

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
    useEffect(() => {
        if (user && user.userId) {
            const data = {
                jwt: token, userId: user?.userId
            }
            dispatch(reqUserPostAction(data))
        }

    }, [user, post.createdPost]);
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
                {isActive === 'POSTS' ?
                    post?.reqUserPost?.map((item) => <UserPostCard post={item} />
                    ) : user?.savedPosts?.map((item) => <UserPostCard post={item} />)}

            </div>
        </div>
    )
}

export default UserMedia
