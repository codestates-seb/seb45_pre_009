import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
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
import KakaoUserInfo from './components/Login/KakaoUserInfo';
import GoogleUserInfo from './components/Login/GoogleUserInfo';
import axios from 'axios';
import Mypage from './pages/Mypage';



function App() {

  const [isLogin,setIsLogin] = useState(sessionStorage.getItem("isLogin"));
  const navigate = useNavigate();

  // const displayName = sessionStorage.getItem("username");
  // const email = sessionStorage.getItem("email");
  const [displayName,setDisplayName] = useState("");
  const [email,setEmail] = useState("");
  const [username,setUserName] = useState("");
  

  useEffect(()=>{
    axios.post("http://3.39.55.166:8080/members/oauth",{
      "displayName": displayName,
      "email": email
    }).then(res=>{
      console.log('회원가입 성공',res)
      axios.post("http://3.39.55.166:8080/login",{
        "username":email
      }).then(loginRes=>{
        console.log("로그인 성공",loginRes)
        setIsLogin(true)
        sessionStorage.setItem("isLogin",true);

        localStorage.setItem("jwt",loginRes.headers.authorization);
        localStorage.setItem("memberId",loginRes.data.memberId)
       
      }).catch(loginErr=>{
        console.log("로그인 실패",loginErr)
        // setIsLogin(false);
        
      })
    }).catch(err=>{
      console.log(err)
      if(err.response && err.response.status === 409) {
        axios.post("http://3.39.55.166:8080/login",{
          "username": email
        }).then(loginRes=>{
          console.log("로그인 성공",loginRes)
          setIsLogin(true);
          sessionStorage.setItem("isLogin",true);

          localStorage.setItem("jwt",loginRes.headers.authorization);
          localStorage.setItem("memberId",loginRes.data.memberId)
          
        }).catch(loginErr=>{
          console.log("로그인 실패",loginErr)
          // setIsLogin(false);
        })
      } else if(err.response && err.response.status === 500){
        axios.post("http://3.39.55.166:8080/login",{
          "username": email
        }).then(loginRes=>{
          console.log("로그인 성공",loginRes)
          setIsLogin(true);
          sessionStorage.setItem("isLogin",true);

          localStorage.setItem("jwt",loginRes.headers.authorization);
          localStorage.setItem("memberId",loginRes.data.memberId)
          
        }).catch(loginErr=>{
          console.log("로그인 실패",loginErr)
          // setIsLogin(false);
        })
      }else {
        console.log("회원가입 실패",err)
        // setIsLogin(false);
      }
    })
  },[displayName, email])
  
  const location = useLocation();
  const showHeaderFooterPaths = [
    "/",
    "/questions",
    "/mypage",
    "/userspage",
    "/oauth",
    "/kakao/callback"

  ];
  const showSidebarPaths = [
    "/",
    "/questions",
    "/userspage",
    "/oauth",
    "/kakao/callback",
    "/mypage"

  ];
  const showHeaderFooter = () =>
    showHeaderFooterPaths.includes(location.pathname);

  const showSidebar = () =>
    showSidebarPaths.includes(location.pathname);


  
  console.log(isLogin);

  return (

    <div className="App flex min-h-full min-w-[auto] flex-col">

      <Header isLogin={isLogin} setIsLogin={setIsLogin}/>
      <div className='flex flex-[1_0_auto] max-w-[1264px] w-full justify-between m-[0_auto] '>
        {showSidebar() && <Sidebar />}
        <Routes>
                <Route path='./oauth' element={<Main/>}></Route>
                <Route path='./kakao/callback' element={<Main/>}></Route>
                <Route path="/" element={<Main></Main>}>
              </Route>
                <Route path="./signup" element={<SignUpPage isLogin={isLogin} setIsLogin={setIsLogin}/>}>
              </Route>
                <Route path="./login" element={<LoginPage isLogin={isLogin} setIsLogin={setIsLogin}/>}>
              </Route>
                <Route path="./questions" element={<Questions/>}>
              </Route>
                <Route path="./questions/:id" element={<QuestionPage/>}>
              </Route>
                <Route path="./userspage" element={<UsersPage/>}>
              </Route>
                <Route path="./questions/ask" element={<Ask/>}>
              </Route>

              <Route path="./mypage" element={<Mypage username={username} setUserName={setUserName} email={email} setEmail={setEmail} />}/>
        </Routes>
      </div>
      {showHeaderFooter() && <Footer />}

      <KakaoUserInfo isLogin={isLogin} setIsLogin={setIsLogin} email={email} setEmail={setEmail} displayName={displayName} setDisplayName={setDisplayName}/>
      <GoogleUserInfo isLogin={isLogin} setIsLogin={setIsLogin} email={email} setEmail={setEmail} displayName={displayName} setDisplayName={setDisplayName}/>


      {/* 조건부로 Footer를 렌더링 */}

      

    </div>


  );
}

export default App;

// 의존성배열에 새로운 값 추가해서 useEffect 두번 실행되지 않도록 해보자.
// 다시는 세션스토리지를 쓰지 않도록.
