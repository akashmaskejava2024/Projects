import { useDispatch } from "react-redux";
import { json, useNavigate } from "react-router";
import { AddUserAction } from "../Action/action";
import { Axios } from "axios";



export const validateLogin= (navigate,userData) => {

    const URL = 'http://localhost:8080/user/login';
    fetch(URL,{
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify(userData)
    })
    .then(res => 
       {
        if(!res.ok){
           throw new Error("Invalid Credentials...");

        }
        return res.json();
       }
    )
    .then(msg => {
        alert(msg.message);
        console.log(msg.message);
        
        navigate("/Dashboard");
    })
    .catch(error => console.log(error));
    

}


export const AddUser = (navigate,dispatch,userData) =>{
    


const URL = 'http://localhost:8080/user';
    fetch(URL,{
        method:'POST',
        headers:{ 'Content-Type':'application/json'},
        body: JSON.stringify(userData)
    })
    .then( res => {
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
        } })
    .then(data => {
        // Use the parsed data directly
        alert(data.msg);
        dispatch(AddUserAction(data));
        navigate("/"); // Navigate after the user is successfully added
    }).catch((error)=>{
        alert(error.message); // Show the error message
        console.error(error);
        
    })
}
export const SendResentTokent = (navigate, email) =>{

    const URL = 'http://localhost:8080/user/resendVerificationlink/' + email ;
    Axios.post(URL)
    .then((res) => 
    
        {
           if(res.ok){
            alert(res);
            navigate('/');
           } else {
            alert("Email is not registered...")
           }
        }
    )
    .catch( (error)=>{
        alert(error);
    })

}