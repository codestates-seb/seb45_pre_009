import GoogleUserInfo from "../components/Login/GoogleUserInfo";
import GoogleLogin from "../components/Login/GoogleLogin";
import KakaoUserInfo from "../components/Login/KakaoUserInfo";
import KakaoLogin from "../components/Login/KakaoLogin";

import kakaoIcon from "../images/Kakao.png";
import googleIcon from "../images/google.png";
import stackIcon from "../images/stackicon.png"


import {useState} from "react";
import NormalLogin from "../components/Login/NormalLogin";


const LoginPage = ({isLogin,setIsLogin}) => {


    return (
        <>
        <div className="flex justify-center items-center min-h-screen bg-[#F2F2F2]">
        


        <div className="flex flex-col justify-center items-center min-h-screen">
        <div className="flex justify-center items-center"><img className="w-[30px] h-[30px]" src={stackIcon} alt="스택오버플로우 아이콘"/></div>
        <div className="w-[300px] h-15 bg-[#FFFFFF] rounded-xl  left-2.5 border-2 border-[#E6E6E6] flex items-center mt-4 mb-2">
        <img className="w-10 h-10 mx-5" src={googleIcon} alt=" 구글 로그인 " />
        <div className="flex flex-col items-start">
          <GoogleLogin isLogin={isLogin} setIsLogin={setIsLogin}/>
        </div>
      </div>
        <div className="w-[300px] h-15 bg-[#FCEC4F] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center">
            <img className="w-10 h-10 mx-5" src={kakaoIcon} alt=" 카카오 로그인" />
            <KakaoLogin isLogin={isLogin} setIsLogin={setIsLogin}/>
        </div>
        <GoogleUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>
        <KakaoUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>
        <div className="w-[300px] h-[300px] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center justify-center mt-4 md-4 bg-[white]">
        <NormalLogin isLogin={isLogin} setIsLogin={setIsLogin}/>
        </div>
        <span className="text-xs mt-10">Don't have an account? Sign up</span>
        <span className="text-xs">Are you an employer? Sign up on Talent</span>
        </div>
        </div>
        </>
    )
}

export default LoginPage