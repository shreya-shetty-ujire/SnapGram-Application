import React from 'react';
import './Dashboard.css';
import HomeRight from '../HomeRight/HomeRight';
import PostCard from '../Posts/PostCard';
import CreatePostModal from '../Posts/CreatePostModal';

const Dashboard = () => {

    return (
        <div>
            <div className='flex justify-center w-full pl-8'>
                <div className="flex justify-between w-[90%]">

                    <div className="w-[40%] px-6 ml-28">
                        <div className="space-y-12 mt-10">
                            {[1, 1].map((item) => <PostCard />)}
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