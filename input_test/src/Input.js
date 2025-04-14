import { useState } from "react";
import { preconnect } from "react-dom";



const Input = () => {

    const [input, setInput] = useState({});
    const [error, setErrors] = useState({});

    const handleNameChange = (e) => {
        const { name, value } = e.target;
        let sanitizedValue = value.slice(0, 20);
        
        
        setInput((prevInput) => ({
            ...prevInput,
            [name]: sanitizedValue
        }));
    
        if (!sanitizedValue) {
            setErrors({
                ...error,
                [name]: "Please Enter the Name"
            });
        } else if (sanitizedValue.length < 5) {
            setErrors({
                ...error,
                [name]: "Please enter at least 5 characters"
            });
        } else {
            setErrors({
                ...error,
                [name]: null
            });
        }

    }


    const handleEmailChange = (e)=>{
         const {name, value} = e.target;

         const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

         if(!value){
            setErrors(
                (prev) =>(
                    {
                        ...prev,
                        [name]:"Email is Required"
                    }
                
            ))
         } else {
            if (emailRegex.test(value)) {
                setInput((prevInput) => ({
                    ...prevInput,
                    [name]: value
                }));
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    [name]: null
                }));
            } else {
                setErrors((prevErrors) => ({
                    ...prevErrors,
                    [name]: "Please enter a valid email"
                }));
            }
         }
            
          

         
           


         

     }



    const handleSubmit = (e) => {
        e.preventDefault();
    }


    return (
        <>

            <div className="container mt-5">
                <h2 className="mb-4">Bootstrap Form in React</h2>
                <form onSubmit={handleSubmit} className="border p-4 rounded shadow">

                    {/* Text Input */}
                    <div className="mb-3">
                        <label className="form-label">Text</label>
                        <input type="text" className="form-control" name="name"  placeholder="Enter the value" onChange={handleNameChange}     value={input.name || ""}  />
                        {error.name ? <span className="text-danger" >{error.name}</span> : null}
                    </div>

                    {/* Email Input */}
                    <div className="mb-3">
                        <label className="form-label">Email</label>
                        <input type="text" className="form-control" name="email" required  onChange={handleEmailChange}/>
                        {error.email ? <span className="text-danger" >{error.email}</span>: null}
                    </div>

                    {/* Password Input */}
                    <div className="mb-3">
                        <label className="form-label">Password</label>
                        <input type="password" className="form-control" name="password" required />
                    </div>

                    {/* Number Input */}
                    <div className="mb-3">
                        <label className="form-label">Number</label>
                        <input type="number" className="form-control" name="number" />
                    </div>

                    {/* Date Picker */}
                    <div className="mb-3">
                        <label className="form-label">Date</label>
                        <input type="date" className="form-control" name="date" />
                    </div>

                    {/* Select Dropdown */}
                    <div className="mb-3">
                        <label className="form-label">Select</label>
                        <select className="form-select" name="select">
                            <option value="">Choose...</option>
                            <option value="option1">Option 1</option>
                            <option value="option2">Option 2</option>
                            <option value="option3">Option 3</option>
                        </select>
                    </div>

                    {/* Checkbox */}
                    <div className="mb-3 form-check">
                        <input type="checkbox" className="form-check-input" name="checkbox" />
                        <label className="form-check-label">Check me out</label>
                    </div>

                    {/* Radio Buttons */}
                    <div className="mb-3">
                        <label className="form-label">Radio Options</label>
                        <div>
                            <input type="radio" className="form-check-input me-2" name="radio" value="option1" /> Option 1
                        </div>
                        <div>
                            <input type="radio" className="form-check-input me-2" name="radio" value="option2" /> Option 2
                        </div>
                    </div>

                    {/* File Upload */}
                    <div className="mb-3">
                        <label className="form-label">Upload File</label>
                        <input type="file" className="form-control" name="file" />
                    </div>

                    {/* Textarea */}
                    <div className="mb-3">
                        <label className="form-label">Textarea</label>
                        <textarea className="form-control" name="textarea" rows="3"></textarea>
                    </div>

                    {/* Submit Button */}
                    <button type="submit" className="btn btn-primary w-100">Submit</button>

                </form>
            </div>



        </>


    )




}
export default Input;