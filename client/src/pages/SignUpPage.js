import GoogleUserInfo from "../components/Login/GoogleUserInfo";
import GoogleLogin from "../components/Login/GoogleLogin";
import KakaoUserInfo from "../components/Login/KakaoUserInfo";
import KakaoLogin from "../components/Login/KakaoLogin";
import SignIn from "../components/Login/SignIn";
import kakaoIcon from "../images/Kakao.png";
import googleIcon from "../images/google.png";

import {useState} from "react";


const SignUpPage = () => {


    return (
        <>
        <button className="w-[300px] h-15 bg-[#FFFFFF] rounded-xl absolute top-[150px] left-2.5 border-2 border-[#E6E6E6] flex items-center">
        <img className="w-10 h-10 mx-5" src={googleIcon} alt=" 구글 로그인 " />
        <div className="flex flex-col items-start">
          <GoogleLogin />
        </div>
      </button>
        <button className="w-[300px] h-15 bg-[#FCEC4F] rounded-xl absolute top-[100px] left-2.5  border-2 border-[#E6E6E6] flex items-center">
            <img className="w-10 h-10 mx-5" src={kakaoIcon} alt=" 카카오 로그인" />
            <KakaoLogin />
        </button>
        <GoogleUserInfo/>
        <KakaoUserInfo/>
        <div className="w-[300px] h-15 rounded-xl absolute top-[200px] left-2.5  border-2 border-[#E6E6E6] flex items-center justify-center">
        <SignIn/>
        </div>
        </>
    )
}

export default SignUpPage