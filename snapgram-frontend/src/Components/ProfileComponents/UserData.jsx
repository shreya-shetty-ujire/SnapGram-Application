import React from 'react';
import { TbCircleDashed } from 'react-icons/tb';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const UserData = ({ user, isFollowing, isReqUser }) => {
  const navigate = useNavigate();
  const {post}= useSelector(store=>store)
  console.log("ppp", post)
  return (
    <div className="py-10 ml-[10rem]">
      <div className="flex flex-row items-center">
        <div className="w-[25%]">
          <img
            className="rounded-full w-48 h-48"
            src={
              user?.userImage ||
              "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"
            }
            alt="Profile"
          />
          <div className="ml-14 text-left">
            <p className="font-bold text-2xl text-gray-800">{user?.name}</p>
            <p className="text-lg text-gray-600">@{user?.username}</p>
            <p className="mt-2 text-base text-gray-700">{user?.bio}</p>
          </div>
        </div>
        <div className="ml-10 space-y-6">
          <div className="flex items-center space-x-8 text-xl">
            <div>
              <span className="font-semibold text-2xl mr-2">{post?.reqUserPost.length}</span>
              <span className="text-lg">posts</span>
            </div>
            <div>
              <span className="font-semibold text-2xl mr-2">{user?.followers?.length}</span>
              <span className="text-lg">followers</span>
            </div>
            <div>
              <span className="font-semibold text-2xl mr-2">{user?.following?.length}</span>
              <span className="text-lg">following</span>
            </div>
          </div>
          <div className="flex items-center space-x-10 text-xl">
            <button
              onClick={() => navigate("/account/edit")}
              className="py-2 rounded-md hover:bg-blue-50"
            >
              Edit Profile
            </button>
            <TbCircleDashed className="text-2xl text-gray-600" />
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserData;
