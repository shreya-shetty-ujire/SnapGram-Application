import React from 'react'
import "./Auth.css"

import logoImage from '../../assets/images/image1.PNG'
import Signin from '../../Components/Authentication/Signin'
import { useLocation } from 'react-router-dom';
import Signup from '../../Components/Authentication/Signup';


const Auth = () => {
  const location = useLocation();
  return (
    <div className="card-container">

      <div className="card1">
        <img src={logoImage} alt="Placeholder" />
      </div>


      <div className="card2-3-container">
        <div className='w-[40vw] lg:w-[23vw]'>
          {location.pathname === "/login" ? <Signin /> : <Signup />}
        </div>
      </div>
    </div>
  )
}
export default Auth
