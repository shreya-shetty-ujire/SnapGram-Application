import React from 'react'
import './SearchComponent.css'
import SearchUserCard from './SearchUserCard'
const SearchComponent = () => {
  return (
    <div className='searchContainer shadow-2xl'>
      <div>
        <div className='pb-4 border-b shadow-sm'>
          <p className='pl-3 text-2xl font-semibold pt-6'>Search</p>
          <input className='searchInput' type='text' placeholder='Search' />
        </div>
        <hr />
        <div className='pt-5 px-3'>
          {[1, 1, 1, 1, 1].map((item) =>
            <SearchUserCard />
          )}
        </div>
      </div>
    </div>
  )
}

export default SearchComponent
