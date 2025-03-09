import React from 'react'
import { TbCircleDashed } from 'react-icons/tb'


const UserData = () => {
    return (
        <div className="py-12 ml-[10rem]">
            <div className="flex flex-row items-center">
                <div className="w-[25%]">
                    <img className="rounded-full w-32 h-32"
                        src="https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg"
                        alt=""
                    />
                </div>
                <div className='space-y-5'>
                    <div className='flex items-center space-x-10 text-xl py-2 mt-2'>
                        <p>_shreya.shetty__</p>
                        <button>Edit Profile</button>
                        <TbCircleDashed></TbCircleDashed>

                    </div>
                    <div className="flex items-center space-x-8 text-xl">
                        <div>
                            <span className="font-semibold mr-2">0</span>
                            <span>posts</span>
                        </div>
                        <div>
                            <span className="font-semibold mr-2">300</span>
                            <span>followers</span>
                        </div>
                        <div >
                            <span className="font-semibold mr-2">200</span>
                            <span>following</span>
                        </div>


                    </div>
                    <div className="items-center py-5 text-xl">
                        <p className='font-semibold'>Shreya Shetty</p>
                        <p> Software Developer </p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default UserData
