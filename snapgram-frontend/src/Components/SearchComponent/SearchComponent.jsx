import React, { useEffect } from 'react'
import './SearchComponent.css'
import SearchUserCard from './SearchUserCard'
import { useDispatch, useSelector } from 'react-redux';
import { searchUserAction } from '../../Redux/User/userActions';
const SearchComponent = ({ onCloseSearch }) => {
  const token = localStorage.getItem("jwtToken");
  const { user } = useSelector((store) => store);
  const dispatch = useDispatch();


  const handleSearch = (e) => {
    dispatch(searchUserAction({
      jwt: token, query: e.target.value
    }))
  }

  return (
    <div className='searchContainer shadow-2xl'>
      <div>
        <div className='pb-4 border-b shadow-sm'>
          <p className='pl-3 text-2xl font-semibold pt-6'>Search</p>
          <input onChange={handleSearch} className='searchInput' type='text' placeholder='Search' />
        </div>
        <hr />
        <div className='pt-5 px-3'>

          {user?.searchUser?.map((item) =>
            <SearchUserCard key={item.userId} user={item} onSelectUser={onCloseSearch} />
          )}
        </div>
      </div>
    </div>
  )
}

export default SearchComponent
