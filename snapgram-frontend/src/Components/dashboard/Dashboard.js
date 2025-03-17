import React , { useEffect, useState } from 'react';
import './Dashboard.css';
import HomeRight from '../HomeRight/HomeRight';
import PostCard from '../Posts/PostCard';
import { useDisclosure } from '@chakra-ui/react';
import { useDispatch, useSelector } from 'react-redux';
import { findUserPostAction } from '../../Redux/Post/Action';


const Dashboard = () => {
    const {isOpen, onOpen, onClose} = useDisclosure()
    const [userIds, setUserIds] = useState();
    const {user, post} = useSelector((store) =>store)

    const dispatch= useDispatch();
    const token = localStorage.getItem("jwtToken")


    // get list of following user ids
    useEffect(()=>{
        if (user && user.reqUser) {
        const newIds = user.reqUser?.following || []; 
            setUserIds([user.reqUser?.userId, ...newIds]); 
        }
    }, [user.reqUser]);

    useEffect(()=>{
        if (user.reqUser && userIds && userIds.length > 0) {
            const data = {
              jwt: token,
              userIds: userIds.join(","),
            };
            dispatch(findUserPostAction(data));
          }
    }, [userIds, post.createdPost, post.deletedPost, user.reqUser]);

    console.log("POstss:",post)

    return (
        <div>
            <div className='flex justify-center w-full pl-8'>
                <div className="flex justify-between w-[90%]">

                    <div className="w-[40%] px-6 ml-28">
                        <div className="space-y-12 mt-10">
                            {post.usersPost.length>0 && 
                            post.usersPost.map((item) => (<PostCard post={item}/>))}
                        </div>
                    </div>
                    <div className='w-[43%] pr-52'>
                        <HomeRight />
                    </div>
                </div>
                
            </div>
        </div>

    );
};

export default Dashboard;