import { useDispatch } from "react-redux";
import { json, useNavigate } from "react-router";
import { AddUserAction } from "../Action/action";
import { Axios } from "axios";



export const validateLogin = (navigate, userData) => {

    const URL = 'http://localhost:8080/user/login';
    fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(res => {
        if (!res.ok) {
            throw new Error(`HTTP Error ${res.status}: ${res.statusText}`);
        }
        return res.json();
    })
    .then(data => {
        if (!data.data) {
            throw new Error("No token received from server!");
        }
        alert(data.message);
        sessionStorage.setItem("token", data.data);
        navigate("/Dashboard");
    })
    .catch(error => {
        console.error("Error occurred:", error);
        alert("An error occurred: " + error.message);
    });

}


export const AddUser = (navigate, dispatch, userData) => {



    const URL = 'http://localhost:8080/user';
    fetch(URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
    })
        .then(res => {
            if (res.status === 201) {
                // 201 Created: parse and handle JSON response 
                return res.json();
            } else if (res.status === 208) {
                // 208 Already Reported: handle without parsing JSON
                alert("User already exists");
                throw new Error("User already exists"); // Skip the next `.then()` by throwing an error
            } else {
                // For other error statuses
                throw new Error("An error occurred while creating the user.");
            }
        })
        .then(data => {
            // Use the parsed data directly
            alert(data.message);
            dispatch(AddUserAction(data.data));
            navigate("/"); // Navigate after the user is successfully added
        }).catch((error) => {
            alert(error.message); // Show the error message
            console.error(error);

        })
}



export const SendResentTokent = (navigate, email) => {

    const emailid= email.email;
    const URL = `http://localhost:8080/user/resendVerificationlink/${emailid}`; // Use backticks for template literals

    fetch(URL)
        .then((res) => {
            console.log("HTTP Status:", res.status); // Log HTTP status code
            if (res.ok) {
                return res.text(); // Parse response as plain text
            } else {
                throw new Error(`HTTP Error: ${res.status} - ${res.statusText}`);
            }
        })
        .then((data) => {
            console.log("Server Response:", data); // Log server response
            alert("Server Response: " + data); // Alert user
            navigate('/'); // Redirect to home
        })
        .catch((error) => {
            console.error("Error Details:", error); // Log error details
            alert("An error occurred: " + error.message); // Alert user about error
        });
};
