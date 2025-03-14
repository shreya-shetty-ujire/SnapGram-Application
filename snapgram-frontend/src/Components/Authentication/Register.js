import React, { useEffect, useMemo, useState } from 'react';
import api from '../../utils/axios';
import { handleApiErrors } from '../../utils/errorHandler';
import './Register.css';
import { useDispatch, useSelector } from 'react-redux';
import { registerUser } from '../../Components/Redux/User/userActions';
import logoImage from '../../assets/images/image1.PNG';
import nameImage from '../../assets/images/name.png';
import {useNavigate} from 'react-router-dom';
import { useToast } from '@chakra-ui/react';


const Register = () => {
  const navigate= useNavigate();
  const dispatch = useDispatch();
  const toast= useToast()
  const [successMessage,setSuccessMessage] = useState('');
  const user = useSelector((state) => state);
  
      useEffect(() => {
          if (user?.username) {
            navigate('/login');
            toast({
              title:`Account created. ${user?.username}`,
              status:'success',
              duration: 5000,
              isClosable: true,
            })
          }
        }, [user.username]);

  const [formData, setFormData] = useState({
    username: '',
    name: '',
    email: '',
    password: ''
  });

  const [isSubmitted, setIsSubmitted] = useState(false);
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };
  const [errors, setErrors] = useState({
    fieldErrors: {},  // Initialize with empty object
    serverError: ''
  });


  const validateForm = () => {
    let formErrors = {};
    let formIsValid = true;

    //username validation
    if (!formData.username) {
      formIsValid = false;
      formErrors.username = 'Username is required';
    }

    //email validation
    if (!formData.email) {
      formIsValid = false;
      formErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      formIsValid = false;
      formErrors.email = 'Email is invalid';
    }

    // Name validation
    if (!formData.name) {
      formIsValid = false;
      formErrors.name = 'Name is required';
    }

    //password validation
    if (!formData.password) {
      formIsValid = false;
      formErrors.password = 'Password is required';
    } else if (formData.password.length < 8) {
      formIsValid = false;
      formErrors.password = "Password should be atleast 8 characters";
    }
    setErrors((prevErrors) => ({
      ...prevErrors,
      fieldErrors: formErrors
    }));
    return formIsValid;
  };



  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsSubmitted(true);
    if (validateForm()) {
      try {
        const response = await api.post('signup', formData);
        console.log('User registered:', response.data);
        dispatch(registerUser({ username: formData.username, name: formData.name }));
        setSuccessMessage('Registration successful! You can now log in.');

        // //Redirect to login page after successful registeration
        // setTimeout(() => {
        //   navigate('/login');
        // }, 3000);

        setFormData({
          username: '',
          name: '',
          email: '',
          password: ''
        });
        setErrors({ fieldErrors: {}, serverError: '' });

        

      } catch (error) {

        handleApiErrors(error, setErrors);
      }
    } else {
      console.log("From has errors. Please enter valid details!");
    }
  };

  const isFormFilled = Object.values(formData)
    .filter((field, index) => index !== 2)
    .every((field) => field !== '');

  return (

    <div className="card-container">
      <div className="card1">
        <img src={logoImage} alt="Placeholder" />
      </div>
      <div className="card2-3-container">
      <div className="card2 mt-8 p-10 flex-col my-auto">
        <img src={nameImage} alt="Snapgram Logo" className="mx-auto" style={{ maxWidth: '65%' , marginTop: '10px', marginBottom: '40px'}} />
        {successMessage && (
        <div className='success-message' style={{ color: 'green', marginTop: '20px', marginBottom: '20px' }}>
          {successMessage}
        </div>
        )}
        <form onSubmit={handleSubmit}>
          <div className="input-container">
            <input
              type="text"
              name="username"
              className='input-field'
              placeholder=" "
              value={formData.username}
              onChange={handleInputChange}
            />
            <label htmlFor="username" className="input-label">Username</label>
            {isSubmitted && errors.fieldErrors?.username && <span className="text-red-500 text-xs">{errors.fieldErrors.username}</span>}
          </div>
          <div className='input-container'>

            <input
              type="text"
              name="email"
              className="input-field"
              placeholder=" "
              value={formData.email}
              onChange={handleInputChange}
            />
            <label htmlFor="Email" className="input-label">Email</label>
            {isSubmitted && errors.fieldErrors?.email && <span className="text-red-500 text-xs">{errors.fieldErrors.email}</span>}
          </div>

          <div className='input-container'>

            <input
              type="text"
              name="name"
              className="input-field"
              placeholder=" "
              value={formData.name}
              onChange={handleInputChange}
            />
            <label htmlFor="name" className="input-label">Name</label>
            {isSubmitted && errors.fieldErrors?.name && <span className="text-red-500 text-xs">{errors.fieldErrors.name}</span>}
          </div>

          <div className='input-container'>

            <input
              type="text"
              name="password"
              className="input-field"
              placeholder=" "
              value={formData.password}
              onChange={handleInputChange}
            />
            <label htmlFor="Password" className="input-label">Password</label>
            {isSubmitted && errors.fieldErrors?.password && <span className="text-red-500 text-xs">{errors.fieldErrors.password}</span>}
          </div>

          <div>
            <p>Female</p>
            <p>Male</p>
          </div>
          {/* server error */}
          {errors.serverError && (
            <div style={{ color: 'red', marginTop: '10px' }}>
              {errors.serverError}
            </div>
          )}
          <button
            type="submit"
            className={`w-full px-4 py-2 mt-4 ${!isFormFilled ? 'bg-blue-300 cursor-not-allowed' : 'bg-blue-500 hover:bg-blue-600'
              } text-white rounded-md`}
            disabled={!isFormFilled}
          >
            Register
          </button>
        </form>
      </div>
      <div className='card3'>
        <p className='text-center'>
          Already registered?{" "}
          <a
            href="/login"
            className="text-blue-500 hover:text-blue-600 font-medium"
          >
            Login here
          </a>
        </p>
      </div>
    </div>
    </div>
  );
};

export default Register;
