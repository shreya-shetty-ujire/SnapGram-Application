

export const handleApiErrors = (error, setErrors) =>{
    if(error.response && error.response.data){
        console.log("Backend Error:", error.response.data); 
        handleServerErrors(error.response.data, setErrors);
    } 
    // network failure, server down
    else if(error.request){
        console.error('No response received from server:', error.request);
        handleNoResponseError(error.request, setErrors);
    } else {
        handleUnexpectedError(error, setErrors);
    }
};


const handleServerErrors = (serverErrors, setErrors) => {
    if (typeof serverErrors === 'object' && serverErrors !== null) {
        if(serverErrors.message){
            setErrors({ fieldErrors: {}, serverError: serverErrors.message });
        }else{
            setErrors({ fieldErrors: serverErrors, serverError: '' });
        }
    }
    else {
        setErrors({ fieldErrors: {}, serverError: 'An error occurred. Please try again.' });
    }
};


const handleNoResponseError = (request, setErrors) => {
    console.error('No response received from server:', request);
    setErrors({fieldErrors: {}, serverError: 'No response from the server. Please check your connection.'});
};

const handleUnexpectedError = (error, setErrors) => {
    console.error('Unexpected error', error);
    setErrors({fieldErrors: {}, serverError: 'An unexpected error occurred. Please try again later.'});
};