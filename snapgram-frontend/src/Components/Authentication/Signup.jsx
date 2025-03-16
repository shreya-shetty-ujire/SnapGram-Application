import React, { useEffect } from 'react';
import nameImage from '../../assets/images/name.png';
import { Box, Button, FormControl, FormErrorMessage, Input, useToast } from '@chakra-ui/react';
import { Field, Form, Formik } from 'formik';
import { object, string } from 'yup';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux'
import { signupAction } from '../../Redux/Auth/Actions'
const validationSchema = object().shape({
  username: string().required('Username is required'),
  email: string().email('Email is invalid').required('Email is required'),
  name: string().required('Name is required'),
  password: string().min(8, 'Password must be at least 8 characters').required('Password is required')
});

const SignUp = () => {
  const initialValues = {
    username: '',
    email: '',
    name: '',
    password: ''
  };

  const navigate = useNavigate();
  const dispatch= useDispatch()
  const {auth}= useSelector((store)=> store);
  const toast= useToast();

  const handleNavigate=()=>navigate("/login")

  const handleSubmit = (values, actions) => {
    console.log("values: ", values);
    dispatch(signupAction(values))
    actions.setSubmitting(false)
  };

  useEffect(()=>{
    if(auth.signup?.username){
      navigate('/login')
      toast({
        title: `Account Created ${auth.signup?.username}`,
        status: "success",
        duration: 3000,
        isClosable: true,
      })
    }

  },auth.signup?.username)

  return (
    <div className="flex flex-col justify-center items-center min-h-screen">
      <div className="shadow-lg rounded-lg p-8 w-96 border">
        <Box p={6} display={'flex'} flexDirection={'column'} alignItems={'center'}>
          <img
            src={nameImage}
            alt="Snapgram Logo"
            className="mx-auto"
            style={{ maxWidth: '65%', marginTop: '10px', marginBottom: '40px' }}
          />

          <Formik
            initialValues={initialValues}
            onSubmit={handleSubmit}
            validationSchema={validationSchema}
          >
            {({ isSubmitting }) => (
              <Form className="w-full space-y-6">
                <Field name="username">
                  {({ field, form }) => (
                    <FormControl isInvalid={form.errors.username && form.touched.username}>
                      <Input {...field} id="username" placeholder="Username" />
                      <FormErrorMessage>{form.errors.username}</FormErrorMessage>
                    </FormControl>
                  )}
                </Field>

                <Field name="email">
                  {({ field, form }) => (
                    <FormControl isInvalid={form.errors.email && form.touched.email}>
                      <Input {...field} id="email" placeholder="Email" />
                      <FormErrorMessage>{form.errors.email}</FormErrorMessage>
                    </FormControl>
                  )}
                </Field>

                <Field name="name">
                  {({ field, form }) => (
                    <FormControl isInvalid={form.errors.name && form.touched.name}>
                      <Input {...field} id="name" placeholder="Name" />
                      <FormErrorMessage>{form.errors.name}</FormErrorMessage>
                    </FormControl>
                  )}
                </Field>

                <Field name="password">
                  {({ field, form }) => (
                    <FormControl isInvalid={form.errors.password && form.touched.password}>
                      <Input {...field} id="password" placeholder="Password" type="password" />
                      <FormErrorMessage>{form.errors.password}</FormErrorMessage>
                    </FormControl>
                  )}
                </Field>

                <Button
                  type="submit"
                  className="w-full"
                  mt={4}
                  colorScheme="blue"
                  isLoading={isSubmitting}
                  isDisabled={isSubmitting}
                >
                  Register
                </Button>
              </Form>
            )}
          </Formik>
        </Box>
      </div>

      <div className="w-96 mt-6 border border-gray-300 bg-white text-center py-3 shadow-md rounded-lg">
        <p>
          Already registered?{' '}
          <span
            onClick={handleNavigate}
            className="text-blue-500 hover:text-blue-600 font-medium"
          >
            Sign In
          </span>
        </p>
      </div>
    </div>
  );
};

export default SignUp;
