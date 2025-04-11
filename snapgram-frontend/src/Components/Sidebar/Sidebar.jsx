import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import nameImage from '../../assets/images/name.png';
import { menuItems } from './menuItems';
import { IoReorderThreeOutline } from "react-icons/io5";
import CreatePostModal from '../Posts/CreatePostModal';
import { useDisclosure } from '@chakra-ui/react';
import SearchComponent from '../SearchComponent/SearchComponent';
import { useDispatch, useSelector } from 'react-redux';
import { LOGOUT } from '../../Redux/Auth/ActionType';

const Sidebar = () => {
    const [activeTab, setActiveTab] = useState();
    const navigate = useNavigate();
    const { isOpen, onClose, onOpen } = useDisclosure();
    const [isSearchVisible, setIsSearchVisible] = useState(false);
    const { user } = useSelector(store => store)
    const dispatch = useDispatch();

    const handleTabClick = (title) => {

        setActiveTab(title);

        if (title === "Profile") {
            navigate(`/${user?.reqUser?.username}`);
        }
        else if (title === "Home") {
            navigate("/dashboard");
        }
        else if (title === "Logout") {
            dispatch({ type: LOGOUT });
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
                        <div className="w-[12rem] pt-16 mb-6">
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
                                key={index} 
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

            <CreatePostModal onClose={onClose} isOpen={isOpen} />


            {isSearchVisible && <SearchComponent onCloseSearch={() => setIsSearchVisible(false)} />}

        </div>
    );
};

export default Sidebar;
