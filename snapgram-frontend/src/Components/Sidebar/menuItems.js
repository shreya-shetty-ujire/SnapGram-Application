import { 
    AiFillHome, AiOutlineHome, AiOutlineSearch, AiOutlineLogout, 
    AiFillHeart, AiOutlineHeart, AiOutlineCompass, AiFillCompass, 
    AiOutlineMessage, AiFillMessage, AiOutlinePlusCircle, AiFillPlusCircle 
} from "react-icons/ai";

import { CgProfile } from "react-icons/cg";
import { RiVideoLine, RiVideoFill } from "react-icons/ri";



export const menuItems = [
    {
        title:"Home", 
        icon: <AiOutlineHome className="text-3xl mr-5"></AiOutlineHome>, 
        activeIcon: <AiFillHome className="text-3xl mr-5"></AiFillHome>
    },
    {
        title:"Search", 
        icon: <AiOutlineSearch className="text-3xl mr-5"></AiOutlineSearch>, 
        activeIcon: <AiOutlineSearch className="text-3xl mr-5"></AiOutlineSearch>
    },
    {
        title:"Explore", 
        icon: <AiOutlineCompass className="text-3xl mr-5"></AiOutlineCompass>, 
        activeIcon: <AiFillCompass className="text-3xl mr-5"></AiFillCompass>
    },
    {
        title:"Reels", 
        icon: <RiVideoLine className="text-3xl mr-5"></RiVideoLine>, 
        activeIcon: <RiVideoFill className="text-3xl mr-5"></RiVideoFill>
    },
    {
        title:"Message", 
        icon: <AiOutlineMessage className="text-3xl mr-5"></AiOutlineMessage>, 
        activeIcon: <AiFillMessage className="text-3xl mr-5"></AiFillMessage>
    },
    {
        title:"Notification", 
        icon: <AiOutlineHeart className="text-3xl mr-5"></AiOutlineHeart>, 
        activeIcon: <AiFillHeart className="text-3xl mr-5"></AiFillHeart>
    },
    {
        title:"Create", 
        icon: <AiOutlinePlusCircle className="text-3xl mr-5"></AiOutlinePlusCircle>, 
        activeIcon: <AiFillPlusCircle className="text-3xl mr-5"></AiFillPlusCircle>
    },
    {
        title:"Profile", 
        icon: <CgProfile className="text-3xl mr-5"></CgProfile>, 
        activeIcon: <CgProfile className="text-3xl mr-5"></CgProfile>
    },
    {
        title:"Logout", 
        icon: <AiOutlineLogout className="text-3xl mr-5"></AiOutlineLogout>, 
        activeIcon: <AiOutlineLogout className="text-3xl mr-5"></AiOutlineLogout>
    }

]