import {
    Button,
    Modal,
    ModalBody,
    ModalCloseButton,
    ModalContent,
    ModalFooter,
    ModalHeader,
    ModalOverlay,
    useDisclosure,
  } from "@chakra-ui/react";
  
  function ChangeProfilePhotoModal({ isOpen, onOpen, onClose, handleProfileImageChange, handleRemovePhoto }) {
    return (
      <Modal onClose={onClose} isOpen={isOpen} isCentered>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader textAlign="center">Modal Title</ModalHeader>
  
          <ModalBody>
            <div className="flex flex-col items-center">
              <label 
                htmlFor="profileImage" 
                className="font-bold py-3 text-blue-600 text-center cursor-pointer text-xs w-full"
              >
                Upload Photo
              </label>
              <input
                            type="file"
                            id="profileImage"
                            className="hidden"
                            onChange={handleProfileImageChange}
                        />

                        <button
                            onClick={handleRemovePhoto}
                            className="font-bold py-3 text-red-600 text-center cursor-pointer text-xs w-full mt-2"
                        >
                            Remove Photo
                        </button>
            </div>
          </ModalBody>
        </ModalContent>
      </Modal>
    );
  }
  
  export default ChangeProfilePhotoModal;
  