import React, { useState } from 'react';
import { FaHome, FaSearch, FaPlusCircle, FaBell, FaUserCircle, FaSignOutAlt } from 'react-icons/fa';
import { Link, useNavigate } from 'react-router-dom';
import nameImage from '../../assets/images/name.png';
import { menuItems } from './menuItems';
import { IoReorderThreeOutline } from "react-icons/io5";
import CreatePostModal from '../Posts/CreatePostModal';
import { useDisclosure } from '@chakra-ui/react';
import SearchComponent from '../SearchComponent/SearchComponent';

const Sidebar = () => {
    const [activeTab, setActiveTab] = useState();
    const navigate = useNavigate();
    const { isOpen, onClose, onOpen } = useDisclosure();
    const [isSearchVisible, setIsSearchVisible] = useState(false);

    const handleTabClick = (title) => {
        setActiveTab(title);

        if (title === "Profile") {
            navigate("/username");
        }
        else if (title === "Home") {
            navigate("/dashboard");
        }
        else if (title === "Logout") {
            localStorage.removeItem('jwtToken');
            navigate("/login");
        }
        else if (title === "Create") {
            onOpen();
        }
        if (title === "Search") {
            setIsSearchVisible(true);
        } else {
            setIsSearchVisible(false);
        }
    };

    return (
        <div className="fixed top-0 h-[100vh] flex items-start">
            <div className={`flex flex-col justify-between h-full ${activeTab === "Search" ? "px-8 pt-14" : "px-10"}`}>
                <div>
                    {!isSearchVisible ? (
                        <div className="w-[9.5rem] pt-10 mb-6">
                            <img src={nameImage} alt="Snapgram Logo" />
                        </div>
                    ) : (
                        <div className="w-12 h-14">
                            <img src="https://www.ambiance-sticker.com/images/Image/sticker-appareil-photo-pour-ipad-ambiance-sticker-mac_iPadPhotoCamera.png" alt="Snapgram Logo" />
                        </div>
                    )}
                    <div className="mt-10">
                        {menuItems.map((item, index) => (
                            <div
                                key={index} // Add a unique key for each item
                                onClick={() => handleTabClick(item.title)}
                                className={`flex items-center mb-6 cursor-pointer text-lg`}
                            >
                                {activeTab === item.title ? item.activeIcon : item.icon}
                                {!isSearchVisible && (
                                    <p className={`ml-3 ${activeTab === item.title ? "font-bold" : "font-normal"}`}>
                                        {item.title}
                                    </p>
                                )}
                            </div>
                        ))}
                    </div>
                </div>

                {!isSearchVisible && (
                    <div className="flex cursor-pointer text-2xl pt-10">
                        <IoReorderThreeOutline className="text-2xl mr-5" />
                        <p>More</p>
                    </div>
                )}
            </div>

            {/* Create Post Modal */}
            <CreatePostModal onClose={onClose} isOpen={isOpen} />

            {isSearchVisible && <SearchComponent />}
        </div>
    );
};

export default Sidebar;
