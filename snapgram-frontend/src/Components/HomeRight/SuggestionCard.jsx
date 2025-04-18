import React from 'react'
import { useDispatch } from 'react-redux';
import { followUserAction } from '../../Redux/User/userActions';

const SuggestionCard = ({ user }) => {
  const dispatch = useDispatch();
  const token = localStorage.getItem("jwtToken");
  const handleFollow = () => {
    dispatch(followUserAction({ userId: user.userId, jwt: token }));
  };
  return (
    <div className='flex justify-between items-center w-full py-2 pr-6 pt-4'>
      <div className='flex items-center'>

        <img className='w-11 h-11 rounded-full' src={
          user?.userImage ||
          "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"
        } alt='' />
        <div className='ml-2'>
          <p className='text-sm font-semibold'>{user.username}</p>
          <p className='text-sm opacity-70'>Popular</p>
        </div>
      </div>
      <button
        className='text-blue-600 font-semibold text-sm'
        onClick={handleFollow}
      >
        Follow
      </button>

    </div>

  )
}

export default SuggestionCard
