
import React, { useState } from 'react'
import { Button, Modal, ModalBody, ModalContent, ModalOverlay } from "@chakra-ui/react";
import { FaPhotoVideo } from 'react-icons/fa';
import './CreatePostModal.css'
import { GrEmoji } from "react-icons/gr"
import {GoLocation} from 'react-icons/go'

const CreatePostModal = ({ onClose, isOpen }) => {

    const [isDragOver, setIsDragOver] = useState(false);
    const [file, setFile] = useState();
    const [caption, setCaption] = useState("");

    const handleDrop = (event) => {  //Triggered when a file is dropped onto the drag area.
        event.preventDefault()
        const droppedFile = event.dataTransfer.files[0];
        if (droppedFile.type.startsWith("image/") || droppedFile.type.startsWith("video/")) {
            setFile(droppedFile)
        }
    }
    // Triggered when a file is dragged over the drop zone.
    const handleDragOver = (event) => {
        event.preventDefault();
        event.dataTransfer.dropEffect = "copy";
        setIsDragOver(true);

    }

    const handleDragLeave = (event) => {
        setIsDragOver(false)

    }

    const handleOnChange = (e) => {
        const file = e.target.files[0];
        if (file && (file.type.startsWith("image/") || file.type.startsWith("image/"))) {
            setFile(file);
        }
        else {
            setFile(null);
            alert("please select an image or video");
        }
    }
    const handleCaptionChange = (e) => {
        setCaption(e.target.value);
    }

    return (
        <div>
            <Modal size={"6xl"} onClose={onClose} isOpen={isOpen} isCentered className='border-r-4'>
                <ModalOverlay />
                <ModalContent>
                    <div className='flex justify-between py-1 px-10 items-center model-header border-b'>
                        <p className='model-title pl-[41%] font-semibold text-lg pb-2 pt-2'>Create New Post</p>
                        <Button variant={"ghost"} size="sm" colorScheme={'blue'}>
                            Share
                        </Button>

                    </div>
                    <ModalBody>
                        <div className='flex h-[75vh] justify-between pb-5'>
                            <div className='w-[50%]'>
                                {!file && <div
                                    onDrop={handleDrop}
                                    onDragOver={handleDragOver}
                                    onDragLeave={handleDragLeave}
                                    className="drag-drop h-full w-full flex flex-col justify-center items-center">

                                    <div className='justify-center items-center'>
                                        <FaPhotoVideo className='text-8xl drag-icon items-center ml-[35%]' />
                                        <p className='text-black pl-5'>Drag Photos or Videos here</p>
                                    </div>

                                    <label htmlFor="file-upload" className='custom-file-upload'>  Select From Computer</label>
                                    <input className='fileInput' type="file" id="file-upload" accept='image/*, video/*' onChange={handleOnChange} />
                                </div>}
                                {file && <img className='max-h-full' src={URL.createObjectURL(file)} alt='' />}

                            </div>
                            <div className='w-[50%] border-l-4'>
                                {/* <div>
                                    <img src='https://wallup.net/wp-content/uploads/2018/10/04/83846-squirrel-humor-funny-face-eyes.jpg' alt=''></img>
                                </div> */}
                                <div className='pl-2 '>
                                    <div className='flex items-center pt-5'>

                                        <img className='w-14 h-14 rounded-full' src='https://www.allprodad.com/wp-content/uploads/2021/03/05-12-21-happy-people.jpg' alt='' />
                                        <div className='ml-2'>
                                            <p className='text-lg font-semibold'>username</p>
                                        </div>
                                    </div>
                                    <div className='pt-3'>
                                        <textarea
                                            placeholder='Write a caption'
                                            className='captionInput'
                                            name='caption'
                                            rows='8'
                                            onChange={handleCaptionChange}></textarea>
                                    </div>
                                    <div className='flex justify-between px-2  border-b-2 text-lg' >
                                        <GrEmoji />
                                        <p className='opacity-70'>{caption?.length}/2,200</p>
                                    </div>
                                    <hr/>
                                    <div className='flex p-2 justify-between items-center'>
                                        <input className='locationInput' type="text" placeholder='Add location' name='location'/>
                                        <GoLocation className='text-4xl pr-3'/>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </ModalBody>
                </ModalContent>
            </Modal>
        </div >
    )
}

export default CreatePostModal
