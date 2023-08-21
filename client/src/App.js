

import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';


import './App.css';
import Header from "./components/Header/Header";
import Footer from './components/Footer/Footer';
import "../src/css/input.css";
import Main from "./pages/main";

import { useState } from 'react';
import SignUpPage from "./pages/SignUpPage";
import Questions from "./pages/questions";
import Ask from "./pages/ask";

import LoginPage from './pages/LoginPage';

import Sidebar from './components/Sidebar/Sidebar';
import UsersPage from './pages/userspage';
import QuestionPage from './pages/questionpage.js';


function App() {

 
  
  const location = useLocation();
  const showHeaderFooterPaths = [
    "/",
    "/questions",
    "/mypage",
    "/userspage"
  ];
  const showSidebarPaths = [
    "/",
    "/questions",
    "/userspage"
  ];
  const showHeaderFooter = () =>
    showHeaderFooterPaths.includes(location.pathname);

  const showSidebar = () =>
    showSidebarPaths.includes(location.pathname);

  const [isLogin,setIsLogin] = useState(sessionStorage.getItem("isLogin"));

  return (

    <div className="App">

      <Header isLogin={isLogin} setIsLogin={setIsLogin}/>
      <div className='flex'>
        {showSidebar() && <Sidebar />}
        <Routes>
                <Route path="/" element={<Main></Main>}>
              </Route>
                <Route path="/signup" element={<SignUpPage isLogin={isLogin} setIsLogin={setIsLogin}/>}>
              </Route>
                <Route path="/login" element={<LoginPage isLogin={isLogin} setIsLogin={setIsLogin}/>}>
              </Route>
                <Route path="/questions" element={<Questions/>}>
              </Route>
                <Route path="/questions/:id" element={<QuestionPage/>}>
              </Route>
                <Route path="/userspage" element={<UsersPage/>}>
              </Route>
                <Route path="/questions/ask" element={<Ask/>}>
              </Route>
        </Routes>
      </div>
      {showHeaderFooter() && <Footer />}
      

    </div>


  );
}

export default App;

