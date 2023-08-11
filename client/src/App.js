import './App.css';
import KakaoLogin from "./components/Login/KakaoLogin"
import GoogleLogin from "./components/Login/GoogleLogin";
import { useState } from 'react';
import KakaoUserInfo from './components/Login/KakaoUserInfo';
import GoogleUserInfo from './components/Login/GoogleUserInfo';

function App() {

  const [isLogin,setIsLogin] = useState(false);

  return (
    <>
    <KakaoLogin/>
    <KakaoUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>
    <GoogleLogin/>
    <GoogleUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>

    </>
  );
}

export default App;

