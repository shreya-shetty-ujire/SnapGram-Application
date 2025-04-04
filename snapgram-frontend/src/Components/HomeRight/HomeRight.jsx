import React from 'react'
import SuggestionCard from './SuggestionCard'
import { useSelector } from 'react-redux'

const HomeRight = () => {
  const {user, post} = useSelector((store) =>store)

    const token = localStorage.getItem("jwtToken")

  return (
    <div>
      
        <div  className='flex justify-between items-center pr-6 pt-14 pb-6'>
          <div className='flex w-full pb-4'>
          <div className="">
            <img className="rounded-full w-16 h-16"
              src={
                user?.userImage ||
                "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"
              } 
              alt=""
            />
          </div>
          <div className='pl-4 pt-2'>
            <p className='font-bold'>{user?.reqUser?.name}</p>
            <p className='text-gray-600'>{user?.reqUser?.username}</p>
          </div>
          </div>
         
            <p className='text-blue-600 font-semibold'>Switch</p>
          
        
      </div>
      <div className='flex justify-between w-full items-center pr-6 pl-4'>
        <p className='text-gray-400 font-semibold text-sm'>Suggested For you</p>
        {/* <p className='text-sm font-bold'>See All</p> */}
      </div>
      
        {user?.popularUsers?.map((item) => <SuggestionCard user={item}/>)}
      

    </div>
  )
}

export default HomeRight
