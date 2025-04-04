import React, { useEffect, useState } from 'react'
import nameImage from '../../assets/images/name.png'
import { Box, Button, FormControl, FormErrorMessage, Input } from '@chakra-ui/react'
import { Field, Form, Formik } from 'formik'
import { object, string } from "yup";
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { signinAction } from '../../Redux/Auth/Actions';
import { getUserProfileAction } from '../../Redux/User/userActions';


const validationSchema = object().shape({
  username: string().required("Username is required"),
  password: string().min(8, "Password must be at lease 8 characters").required("Password is required")
})

const Signin = () => {
  const initialValues = { username: "", password: "" }
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { user } = useSelector(store => store);
  const token = localStorage.getItem("jwtToken");
  const [username, setUsername] = useState("");

  const handleSubmit = (values, actions) => {
    setUsername(values.username); 
    dispatch(signinAction(values, actions.setErrors));
    actions.setSubmitting(false);
  }

  useEffect(() => {

    if (user?.reqUser?.username) {
      navigate("/dashboard")
    }
  }, [token, user.reqUser])

  useEffect(() => {
    if (token && username) {
       dispatch(getUserProfileAction(token, username));
      // dispatch(getUserProfileAction(token, user.reqUser?.username));
    }

  }, [token])

  const handleNavigate = () => navigate("/signup")


  return (
    <div className="flex flex-col justify-center items-center min-h-screen">
      <div className="shadow-lg rounded-lg p-8 w-96 border">

        <Box p={6}
          display={'flex'}
          flexDirection={'column'}
          alignItems={'center'}>
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

            {(formikProps) =>
              <Form className="w-full space-y-6">

                <Field name="username">
                  {({ field, form }) => (<FormControl isInvalid={form.errors.username && form.touched.username}>

                    <Input
                      classname="w-full"
                      {...field}
                      id='username'
                      placeholder='Username'>
                    </Input>
                    <FormErrorMessage>{form.errors.username}</FormErrorMessage>
                  </FormControl>)}
                </Field>

                <Field name="password">
                  {({ field, form }) => (
                    <FormControl isInvalid={form.errors.password && form.touched.password}>

                      <Input
                        classname="w-full"
                        {...field}
                        id='password'
                        placeholder='Password'>
                      </Input>
                      <FormErrorMessage>{form.errors.password}</FormErrorMessage>
                    </FormControl>)}
                </Field>
                {formikProps.errors.serverError && (
                  <div className="text-red-500 mb-4">{formikProps.errors.serverError}</div>
                )}
                <Button type="submit" className="w-full" mt={4} colorScheme='blue' isLoading={formikProps.isSubmitting}>Login</Button>


              </Form>}

          </Formik>
        </Box>

      </div>
      <div className="w-96 mt-6 border border-gray-300 bg-white text-center py-3 shadow-md rounded-lg">
        <p>
          Don't have an account?{" "}
          <span onClick={handleNavigate} className="text-blue-500 hover:text-blue-600 font-medium">
            Sign Up
          </span>
        </p>
      </div>

    </div>
  )
}

export default Signin
