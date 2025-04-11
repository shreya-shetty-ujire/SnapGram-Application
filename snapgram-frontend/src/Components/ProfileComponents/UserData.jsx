import React, { useState } from 'react';
import { Modal, ModalBody, ModalContent, ModalOverlay } from "@chakra-ui/react";
import { useNavigate } from 'react-router-dom';
import { BsThreeDots } from 'react-icons/bs';
import { FaRegComment } from 'react-icons/fa';
import { AiOutlineHeart, AiFillHeart } from 'react-icons/ai';
import { BsBookmark, BsBookmarkFill } from 'react-icons/bs';
import { useSelector } from 'react-redux';

const UserData = ({ user, isFollowing, isReqUser }) => {
  const navigate = useNavigate();
  const { post } = useSelector(store => store);

  const [showModal, setShowModal] = useState(false);
  const [modalTitle, setModalTitle] = useState('');
  const [modalUsers, setModalUsers] = useState([]);

  const handleOpenModal = (title, list) => {
    setModalTitle(title);
    setModalUsers(list);
    setShowModal(true);
  };
  console.log("user", user)
  return (
    <div className="py-10 ml-[10rem]">
      <div className="flex flex-row items-center">
        <div className="w-[25%]">
          <img
            className="rounded-full w-48 h-48"
            src={
              user?.userImage ||
              "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"
            }
            alt="Profile"
          />
          <div className="ml-14 text-left">
            <p className="font-bold text-2xl text-gray-800">{user?.name}</p>
            <p className="text-lg text-gray-600">@{user?.username}</p>
            <p className="mt-2 text-base text-gray-700">{user?.bio}</p>
          </div>
        </div>
        <div className="ml-10 space-y-6">
          <div className="flex items-center space-x-8 text-xl">
            <div>
              <span className="font-semibold text-2xl mr-2">{post?.reqUserPost?.length}</span>
              <span className="text-lg">posts</span>
            </div>
            <div className="cursor-pointer" onClick={() => handleOpenModal("Followers", user?.followers)}>
              <span className="font-semibold text-2xl mr-2">{user?.followers?.length}</span>
              <span className="text-lg">followers</span>
            </div>
            <div className="cursor-pointer" onClick={() => handleOpenModal("Following", user?.following)}>
              <span className="font-semibold text-2xl mr-2">{user?.following?.length}</span>
              <span className="text-lg">following</span>
            </div>
          </div>
          <div className="flex items-center space-x-10 text-xl">
            <button
              onClick={() => navigate("/account/edit")}
              className="py-2 rounded-md hover:bg-blue-50"
            >
              Edit Profile
            </button>
            <BsThreeDots className="text-2xl text-gray-600" />
          </div>
        </div>
      </div>

      {showModal && (
        <Modal size={"3xl"} onClose={() => setShowModal(false)} isOpen={showModal} isCentered>
          <ModalOverlay />
          <ModalContent>
            <ModalBody>
              <div className="p-6">
                <h2 className="font-bold text-2xl">{modalTitle}</h2>
                <div className="space-y-4 mt-4">
                  {modalUsers?.length > 0 ? (
                    modalUsers.map((item, index) => (
                      <div key={index} className="flex items-center space-x-3">
                        <img
                          src={item?.userImage || "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"}
                          alt="avatar"
                          className="w-14 h-14 rounded-full"
                        />
                        <div>
                          <p className="font-semibold text-xl">{item?.name}</p>
                          <p className=" text-gray-600">@{item?.username}</p>
                        </div>
                      </div>
                    ))
                  ) : (
                    <p>No users found.</p>
                  )}
                </div>
              </div>
            </ModalBody>
          </ModalContent>
        </Modal>
      )}
    </div>
  );
};

export default UserData;
