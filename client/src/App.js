
import { BrowserRouter, Route, Routes, useLocation } from 'react-router-dom';

import './App.css';
import Header from "./components/Header/Header";
import Footer from './components/Footer/Footer';
import "../src/css/input.css";
import Main from "./pages/main";
import { useState } from 'react';
import SignUpPage from "./pages/SignUpPage";

import LoginPage from './pages/LoginPage';

import Sidebar from './components/Sidebar/Sidebar';


function App() {

  

  
  const location = useLocation();
  const showHeaderFooterPaths = [
    "/",
    "/main",
    "/mypage",

  ]
  const showHeaderFooter = () =>
    showHeaderFooterPaths.includes(location.pathname);
  

  const [isLogin,setIsLogin] = useState(false);

  return (

    <div className="App">

      <Header isLogin={isLogin} setIsLogin={setIsLogin}/>

      <div id='container' className='w-screen h-screen'>
      <Main></Main>
      <Sidebar />
      </div>

      <SignUpPage isLogin={isLogin} setIsLogin={setIsLogin}/>
      <LoginPage isLogin={isLogin} setIsLogin={setIsLogin}/>
      {/* 조건부로 Footer를 렌더링 */}
      {showHeaderFooter() && <Footer />}

    </div>


  );
}

export default App;

