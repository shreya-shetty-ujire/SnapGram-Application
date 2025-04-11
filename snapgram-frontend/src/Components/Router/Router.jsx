import React from 'react';
import { Routes, Route, useLocation, Navigate } from 'react-router-dom';
import Sidebar from '../Sidebar/Sidebar';
import Dashboard from '../dashboard/Dashboard';
import Profile from '../Profile/Profile';
import Auth from '../../Pages/Auth/Auth';
import EditAccountDetails from '../EditAccount/EditAccountDetails';
import { useSelector } from 'react-redux';

const Router = () => {
    const location = useLocation();
    const { isAuthenticated } = useSelector((store) => store.auth);

    // 🔐 Route protection: redirect to login if not authenticated
    if (!isAuthenticated && location.pathname !== "/login" && location.pathname !== "/signup") {
        return <Navigate to="/login" />;
    }

    return (
        <div>
            {(location.pathname !== "/login" && location.pathname !== "/signup") && (
                <div className='flex'>
                    <div className='w-[20%]'>
                        <Sidebar />
                    </div>
                    <div className="w-[80%]">
                        <Routes>
                            <Route path="/" element={<Dashboard />} />
                            <Route path="/dashboard" element={<Dashboard />} />
                            <Route path="/:username" element={<Profile />} />
                            <Route path="/comment/:postId" element={<Dashboard />} />
                            <Route path="/account/edit" element={<EditAccountDetails />} />
                        </Routes>
                    </div>
                </div>
            )}
            {(location.pathname === "/login" || location.pathname === "/signup") && (
                <div>
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

export default Router;
