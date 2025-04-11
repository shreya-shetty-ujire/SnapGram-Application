import React, { useEffect, useState } from "react";
import {
  Button,
  Checkbox,
  FormControl,
  FormHelperText,
  FormLabel,
  Input,
  Stack,
  Textarea,
  useDisclosure,
  useToast,
} from "@chakra-ui/react";
import { useFormik } from "formik";
import { useDispatch, useSelector } from "react-redux";
import ChangeProfilePhotoModal from "./ChangeProfilePhotoModal";
import { editUserAction, getUserProfileAction } from "../../Redux/User/userActions";
import { uploadToCloudnary } from "../Config/UploadToCloud";
import { useNavigate } from "react-router-dom";


const EditAccount = () => {
  const { user } = useSelector((store) => store);
  const dispatch = useDispatch();
  const toast = useToast();
  const { isOpen, onOpen, onClose } = useDisclosure();

  const token = localStorage.getItem("jwtToken");
  const [imageFile, setImageFile] = useState(null);
  const navigate= useNavigate();

  const [initialValues, setInitialValues] = useState({
    name: "",
    username: "",
    email: "",
    bio: "",
    mobile: "",
    gender: "",
    website: "",
    private: false,
  });
  useEffect(() => {
    if (token) {
      dispatch(getUserProfileAction(token,  user?.reqUser?.username));
    }
  }, [token, dispatch]);

  useEffect(() => {
    if (user.reqUser) {
      const newValue = {...initialValues};
      for (let key in newValue) {
        if (user.reqUser[key]) {
          newValue[key] = user.reqUser[key];
        }
      }
      setInitialValues(newValue);
    }
  }, [user.reqUser]);
  useEffect(() => {
    if (user.reqUser) {
      setInitialValues((prev) => ({
        ...prev,
        ...user.reqUser,
        userImage: user.reqUser.userImage, // Ensure userImage updates
      }));
    }
  }, [user.reqUser]);

  const formik = useFormik({
    enableReinitialize:true,
    initialValues: { ...initialValues },
    onSubmit: (values) => {
      const data = {
        jwt: token,
        data: { ...values, userId: user.reqUser?.userId },
      };
      
      dispatch(editUserAction(data))
      .then(() => {
        dispatch(getUserProfileAction(token, values.username));
        toast({
          title: "Account updated successfully!",
          status: "success",
          duration: 5000,
          isClosable: true,
        });
        navigate(`/${values.username}`);
      })
      .catch((error) => {
        toast({
          title: "Failed to update account",
          status: "error",
          duration: 5000,
          isClosable: true,
        });
      });
  },
  });

  const handleProfileImageChange = async (event) => {
    const selectedFile = event.target.files[0];
    if (selectedFile){
      const userImage = await uploadToCloudnary(selectedFile);
    setImageFile(userImage);

    const data = {
      jwt: token,
      data: { userImage, userId: user.reqUser?.userId },
    };
    dispatch(editUserAction(data));
    }

    
    onClose();
  };
  const handleRemovePhoto = async () => {
    const defaultImage = "https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg"; // Default blank image

    const data = {
        jwt: token,
        data: { userImage: defaultImage, userId: user.reqUser?.userId }, // Reset image
    };

    setImageFile(defaultImage); // Update UI immediately
    dispatch(editUserAction(data))
        .then(() => {
            dispatch(getUserProfileAction(token, user.reqUser?.username));
            toast({
                title: "Profile photo removed!",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
        })
        .catch(() => {
            toast({
                title: "Failed to remove profile photo",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
        });

    onClose(); 
};

  return (
    <div className="border rounded-md p-10 px-32">
      <div className="flex pb-7 items-center">
        <div className="w-[15%]">
          <img
            className="w-20 h-20 rounded-full"
            src={
              user.reqUser?.userImage  ||
              imageFile ||
              "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png"
            }
            alt="Profile"
          />
        </div>
        <div className="ml-5">
          <p className="text-lg font-semibold">{user.reqUser?.username}</p>
          <p onClick={onOpen} className="font-bold text-blue-800 cursor-pointer">
            Change Profile Photo
          </p>
        </div>
      </div>

      <form onSubmit={formik.handleSubmit}>
        <Stack spacing="6">
          {/* Name */}
          <FormControl className="flex" id="name">
            <FormLabel className="w-[15%]">Name</FormLabel>
            <div className="w-full">
              <Input
                type="text"
                placeholder="Name"
                className="w-full"
                {...formik.getFieldProps("name")}
              />
              <FormHelperText className="text-xs">
                Help people discover your account by using a name you're known by.
              </FormHelperText>
              <FormHelperText className="text-xs">
                You can only change your name twice within 14 days.
              </FormHelperText>
            </div>
          </FormControl>

          {/* Username */}
          <FormControl className="flex" id="username">
            <FormLabel className="w-[15%]">Username</FormLabel>
            <div className="w-full">
              <Input
                type="text"
                placeholder="Username"
                className="w-full"
                {...formik.getFieldProps("username")}
              />
              <FormHelperText className="text-xs">
                You can change your username back within 14 days.
              </FormHelperText>
            </div>
          </FormControl>

          {/* Website */}
          <FormControl className="flex" id="website">
            <FormLabel className="w-[15%]">Website</FormLabel>
            <div className="w-full">
              <Input
                type="text"
                placeholder="Website"
                className="w-full"
                {...formik.getFieldProps("website")}
              />
              <FormHelperText className="text-xs">
                Editing links is only available on mobile.
              </FormHelperText>
            </div>
          </FormControl>

          {/* Bio */}
          <FormControl className="flex" id="bio">
            <FormLabel className="w-[15%]">Bio</FormLabel>
            <div className="w-full">
              <Textarea
                placeholder="Bio"
                className="w-full"
                {...formik.getFieldProps("bio")}
              />
            </div>
          </FormControl>

          {/* Personal Information Section */}
          <div className="py-10">
            <p className="font-bold text-sm">Personal Information</p>
            <p className="text-xs">
              Provide your personal information, even if this account is for a business or pet. This won't be part of your public profile.
            </p>
          </div>

          {/* Email */}
          <FormControl className="flex" id="email">
            <FormLabel className="w-[15%]">Email address</FormLabel>
            <div className="w-full">
              <Input
                type="email"
                placeholder="Email"
                className="w-full"
                {...formik.getFieldProps("email")}
              />
            </div>
          </FormControl>

          {/* Phone */}
          <FormControl className="flex" id="mobile">
            <FormLabel className="w-[15%]">Phone number</FormLabel>
            <div className="w-full">
              <Input
                type="tel"
                placeholder="Phone"
                className="w-full"
                {...formik.getFieldProps("mobile")}
              />
            </div>
          </FormControl>

          {/* Gender */}
          <FormControl className="flex" id="gender">
            <FormLabel className="w-[15%]">Gender</FormLabel>
            <div className="w-full">
              <Input
                type="text"
                placeholder="Gender"
                className="w-full"
                {...formik.getFieldProps("gender")}
              />
            </div>
          </FormControl>

          {/* Submit Button */}
          <div>
            <Button colorScheme="blue" type="submit">
              Submit
            </Button>
          </div>
        </Stack>
      </form>

      {/* Profile Photo Modal */}
      <ChangeProfilePhotoModal
        handleProfileImageChange={handleProfileImageChange}
        handleRemovePhoto={handleRemovePhoto}
        isOpen={isOpen}
        onClose={onClose}
        onOpen={onOpen}
      />
    </div>
  );
};

export default EditAccount;
