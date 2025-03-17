import React, { useState } from 'react';
import { Routes, Route, useLocation } from 'react-router-dom';
import Sidebar from '../Sidebar/Sidebar';
import Dashboard from '../dashboard/Dashboard';
import Profile from '../Profile/Profile';
import Auth from '../../Pages/Auth/Auth';


const Router = () => {

    const  location  = useLocation();
    return (
        <div>
            {(location.pathname !== "/login" && location.pathname !== "/signup")  && (
                <div className='flex'>
                    <div className='w-[20%]'>
                        <Sidebar />
                    </div>
                    <div className="w-[80%]">
                        <Routes>

                            <Route path="/dashboard" element={<Dashboard />} />
                            <Route path="/username" element={<Profile />} />
                            <Route path="/comment/:postId" element={<Dashboard />} /> 
                        </Routes>
                    </div>
                </div>
            )}:{(location.pathname === "/login" || location.pathname === "/signup") && (
                <div>
                    {/* <Routes>
                        <Route path="/" element={<Register />} /> 
                        <Route path="/register" element={<Register />} />
                        <Route path="/login" element={<Login />} />
                    </Routes> */}
                    <Routes>
                        <Route path="/" element={<Auth />} /> 
                        <Route path="/signup" element={<Auth />} />
                        <Route path="/login" element={<Auth />} />
                    </Routes>
                    
                </div>
            )}
                
                
                
            
        </div>
    );
}

export default Router
