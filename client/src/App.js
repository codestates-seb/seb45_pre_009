import './App.css';

import { useState } from 'react';

import SignUpPage from "./pages/SignUpPage";


function App() {

  

  

  

  const [isLogin,setIsLogin] = useState(false);

  return (
    <>
    <SignUpPage/>
    </>
  );
}

export default App;

