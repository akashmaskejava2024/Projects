import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import { Login } from './Users/Login';
import UserRegistration from './Users/UserRegistration';
import ResendToken from './Users/ResendToken';
import Dashboard from './Dashboard/dashboard';
function App() {
  return (
    <Routes>
    <Route path='/' element={<Login />}></Route>
     <Route path='/Register' element={<UserRegistration/>} ></Route> 
     <Route path='/ResendToken' element={<ResendToken/>}></Route>
     <Route path='/Dashboard' element={<Dashboard/>}></Route>
     
    </Routes>
  );
}

export default App;
