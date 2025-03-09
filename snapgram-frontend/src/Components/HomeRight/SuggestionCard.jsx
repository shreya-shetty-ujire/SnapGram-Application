import React from 'react'

const SuggestionCard = () => {
    return (
        <div className='flex justify-between items-center w-full py-2 pr-6 pt-4'>
            <div className='flex items-center'>
                
                    <img className='w-11 h-11 rounded-full' src='https://www.allprodad.com/wp-content/uploads/2021/03/05-12-21-happy-people.jpg' alt=''/>
                    <div className='ml-2'>
                        <p className='text-sm font-semibold'>username</p>
                        <p className='text-sm opacity-70'>Follows you</p>
                    </div>
                </div>
                <p className='text-blue-600 font-semibold text-sm'>Follow</p>
            </div>

    )
}

export default SuggestionCard
