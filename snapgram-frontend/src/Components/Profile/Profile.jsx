import React from 'react'
import UserData from '../ProfileComponents/UserData'
import UserMedia from '../ProfileComponents/UserMedia'

const Profile = () => {
    return (
        <div className="px-20 w-full pr-80">
            <div>
                <UserData />
            </div>
            <div>
                <UserMedia />
            </div>
        </div>

    )
}

export default Profile
