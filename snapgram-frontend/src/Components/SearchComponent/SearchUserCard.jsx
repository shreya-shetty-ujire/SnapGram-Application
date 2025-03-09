import React from 'react'

const SearchUserCard = () => {
  return (
    <div className='py-3 cursor-pointer'>
    <div className='flex px-4 items-center'>
      <img className='w-10 h-10 rounded-full' src='https://wallpapercave.com/wp/wp3720070.jpg' alt=''/>
   
      <div className='ml-3'>
        <p>Full Name</p>
        <p className='opacity-70'>username</p>

      </div>
    </div>
    </div>
  )
}

export default SearchUserCard
