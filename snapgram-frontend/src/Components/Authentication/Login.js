import React, {  useState } from 'react';
import logoImage from '../../assets/images/image1.PNG';
import nameImage from '../../assets/images/name.png';
import api from '../../utils/axios';
import { handleApiErrors } from '../../utils/errorHandler';
import './Login.css';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        username: '',
        password: ''
    });

    const [isSubmitted, setIsSubmitted] = useState(false);
    const [passwordVisible, setpasswordVisible]=useState(false);
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
        //password validation
        if (!formData.password) {
            formIsValid = false;
            formErrors.password = 'Password is required';
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
                const response = await api.post('login', {
                    username: formData.username,
                    password : formData.password
                });

                //backend sends the token if successful
                if(response.status === 200){
                    const jwtToken = response.data;
                    
                    localStorage.setItem('jwtToken', jwtToken);

                    navigate('/dashboard');
                }
                 
    
                setFormData({
                    username: '',
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
                                type={passwordVisible ? "text" : "password"}
                                name="password"
                                className="input-field"
                                placeholder=" "
                                value={formData.password}
                                onChange={handleInputChange}
                            />
                            <label htmlFor="Password" className="input-label">Password</label>
                            {isSubmitted && errors.fieldErrors?.password && <span className="text-red-500 text-xs">{errors.fieldErrors.password}</span>}
                            <button
                                type="button" className="absolute right-5 text-blue-500 top-2 transform focus:outline-none" 
                                onClick={() => setpasswordVisible(!passwordVisible)}>
                                {passwordVisible ? 'Hide':'Show'}
                            </button>
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
                            Login
                        </button>
                    </form>
                </div>
                <div className='card3'>
                    <p className='text-center'>
                        Don't have an account?{" "}
                        <a
                            href="/register"
                            className="text-blue-500 hover:text-blue-600 font-medium"
                        >
                            Sign Up
                        </a>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Login;

