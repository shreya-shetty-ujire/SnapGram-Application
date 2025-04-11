import React from 'react'
import { useNavigate } from 'react-router-dom';

const SearchUserCard = ({ user, onSelectUser }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/${user?.username}`);
    onSelectUser && onSelectUser();
  };

  return (
    <div onClick={handleClick} className='py-3 cursor-pointer'>
      <div className='flex px-4 items-center'>
        <img className='w-10 h-10 rounded-full' src={
          user?.userImage ||
          "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"
        } alt='' />

        <div className='ml-3'>
          <p>{user.name}</p>
          <p className='opacity-70'>{user.username}</p>

        </div>
      </div>
    </div>
  )
}

export default SearchUserCard
