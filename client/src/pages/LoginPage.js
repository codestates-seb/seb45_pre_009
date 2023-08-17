import GoogleUserInfo from "../components/Login/GoogleUserInfo";
import GoogleLogin from "../components/Login/GoogleLogin";
import KakaoUserInfo from "../components/Login/KakaoUserInfo";
import KakaoLogin from "../components/Login/KakaoLogin";

import kakaoIcon from "../images/Kakao.png";
import googleIcon from "../images/google.png";


import {useState} from "react";
import NormalLogin from "../components/Login/NormalLogin";


const LoginPage = () => {


    return (
        <>
        <div className="flex justify-center items-center min-h-screen bg-[#F2F2F2]">
        


        <div className="flex flex-col justify-center items-center min-h-screen">
        <button className="w-[300px] h-15 bg-[#FFFFFF] rounded-xl  left-2.5 border-2 border-[#E6E6E6] flex items-center mt-4 mb-2">
        <img className="w-10 h-10 mx-5" src={googleIcon} alt=" 구글 로그인 " />
        <div className="flex flex-col items-start">
          <GoogleLogin />
        </div>
      </button>
        <button className="w-[300px] h-15 bg-[#FCEC4F] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center">
            <img className="w-10 h-10 mx-5" src={kakaoIcon} alt=" 카카오 로그인" />
            <KakaoLogin />
        </button>
        <GoogleUserInfo/>
        <KakaoUserInfo/>
        <div className="w-[300px] h-[350px] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center justify-center mt-4 md-4 bg-[white]">
        <NormalLogin/>
        </div>
        </div>
        </div>
        </>
    )
}

export default LoginPage