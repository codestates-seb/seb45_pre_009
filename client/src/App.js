import './App.css';
import "../src/css/input.css";
import Main from "./pages/main";


import { useState } from 'react';

import SignUpPage from "./pages/SignUpPage";


function App() {

  

  

  

  const [isLogin,setIsLogin] = useState(false);

  return (
    <>
      <div id='container' className='w-screen h-screen'>
      <Main></Main>
    </div>
    </>
  );
}

export default App;

