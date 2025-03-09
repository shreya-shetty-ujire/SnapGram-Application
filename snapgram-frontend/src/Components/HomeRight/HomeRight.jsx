import React from 'react'
import SuggestionCard from './SuggestionCard'

const HomeRight = () => {
  return (
    <div>
      
        <div  className='flex justify-between items-center pr-6 pt-14 pb-6'>
          <div className='flex w-full pb-4'>
          <div className="">
            <img className="rounded-full w-11 h-11"
              src="https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg"
              alt=""
            />
          </div>
          <div className='pl-4'>
            <p className='font-bold'>_shreya.shetty__</p>
            <p className='text-gray-600'>Shreya Shetty</p>
          </div>
          </div>
          {/* <div> */}
            <p className='text-blue-600 font-semibold text-sm'>Switch</p>
          {/* </div> */}
        
      </div>
      <div className='flex justify-between w-full items-center pr-6'>
        <p className='text-gray-400 font-semibold text-sm'>Suggested For you</p>
        <p className='text-sm font-bold'>See All</p>
      </div>
      
        {[1, 1, 1, 1].map((item) => <SuggestionCard />)}
      

    </div>
  )
}

export default HomeRight
