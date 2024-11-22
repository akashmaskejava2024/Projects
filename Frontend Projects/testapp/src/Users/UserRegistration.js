import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import './UserRegistration.css';
import { AddUser } from "../ActionCreator/UserActionCreator";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

// Validation Schema using Yup
const validationSchema = Yup.object().shape({
    name: Yup.string().required("Name is required"),
    email: Yup.string().email("Invalid email").required("Email is required"),
    username: Yup.string()
        .trim()
        .required("Username is required")
        .min(4, "Minimum 4 characters")
        .max(10, "Maximum 10 characters")
        .matches(/^[a-zA-Z]+$/, "Username can only contain letters"),
    password: Yup.string()
        .trim()
        .required("Password is required")
        .min(8, "Minimum 8 characters")
        .matches(/[A-Z]/, "Password must contain at least one uppercase letter")
        .matches(/[a-z]/, "Password must contain at least one lowercase letter")
        .matches(/[0-9]/, "Password must contain at least one number")
        .matches(/[\W_]/, "Password must contain at least one special character")
});

const UserRegistration = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    return (
        <Formik
            initialValues={{
                name: '',
                email: '',
                username:'',
                password: ''
            }}
            validationSchema={validationSchema}
            onSubmit={(user) => {
                console.log("Form submitted with data:", user);  // Debug log
                AddUser(navigate, dispatch, user).finally(() => {
                    // Handle any final logic after submission
                });
            }}
        >
            {({ errors, touched, isValid, dirty }) => (
                <div className="container mt-5 p-5">
                    <h2>User Registration</h2>
                    <Form>
                        {/* Name */}
                        <div className="form-group">
                            <label htmlFor="name">Name</label>
                            <Field
                                type="text"
                                id="name"
                                name="name"
                                className={`form-control ${errors.name && touched.name ? 'is-invalid' : ''}`}
                            />
                            <ErrorMessage className="ErrorMsg" component="span" name="name" />
                        </div>

                        {/* Email */}
                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <Field
                                type="email"
                                id="email"
                                name="email"
                                className={`form-control ${errors.email && touched.email ? 'is-invalid' : ''}`}
                            />
                            <ErrorMessage className="ErrorMsg" component="span" name="email" />
                        </div>

                       

                        {/* Username */}
                        <div className="form-group">
                            <label htmlFor="username">Username</label>
                            <Field
                                type="text"
                                id="username"
                                name="username"
                                className={`form-control ${errors.username && touched.username ? 'is-invalid' : ''}`}
                            />
                            <ErrorMessage className="ErrorMsg" component="span" name="username" />
                        </div>

                        {/* Password */}
                        <div className="form-group">
                            <label htmlFor="password">Password</label>
                            <Field
                                type="password"
                                id="password"
                                name="password"
                                className={`form-control ${errors.password && touched.password ? 'is-invalid' : ''}`}
                            />
                            <ErrorMessage className="ErrorMsg" component="span" name="password" />
                        </div>

                        {/* Submit Button */}
                        <div className="d-flex justify-content-end w-100">
                            <button
                                type="submit"
                                className="btn btn-primary mt-5"
                                disabled={!isValid || !dirty}
                            >
                                Submit
                            </button>
                        </div>
                    </Form>
                </div>
            )}
        </Formik>
    );
}

export default UserRegistration;
