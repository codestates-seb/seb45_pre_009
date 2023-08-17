import GoogleUserInfo from "../components/Login/GoogleUserInfo";
import GoogleLogin from "../components/Login/GoogleLogin";
import KakaoUserInfo from "../components/Login/KakaoUserInfo";
import KakaoLogin from "../components/Login/KakaoLogin";
import SignIn from "../components/Login/SignIn";
import kakaoIcon from "../images/Kakao.png";
import googleIcon from "../images/google.png";
import badgesicon from "../images/badgesicon.png";
import questionicon from "../images/questionicon.png";
import tagicon from "../images/tagicon.png";
import votingicon from "../images/votingicon.png";

import {useState} from "react";


const SignUpPage = ({isLogin,setIsLogin}) => {


    return (
        <>
        <div className="flex justify-center items-center min-h-screen bg-[#F2F2F2]">
        <div className="flex flex-col mr-4">
            <h1 className="fs-headline1 mb-10 lh-xs text-2xl text-bold">Join the Stack Overflow community</h1>
            
            <h4 className="flex"><img className="w-[30px] h-[30px]" src={questionicon} alt="질문아이콘"/>Get unstuck - ask a question</h4>
            <h4 className="mt-2 flex"><img className="w-[30px] h-[30px]" src={votingicon} alt="투표아이콘"/>Unlock new privileges like voting and commenting</h4>
            <h4 className="mt-2 flex"><img className="w-[30px] h-[30px]" src={tagicon} alt="태그아이콘"/>Save your favorite questions, answers, watch tags, and more</h4>
            <h4 className="mt-2 flex"><img className="w-[30px] h-[30px]" src={badgesicon} alt="뱃지아이콘"/>Earn reputation and badges</h4>
            
            <h6 className="text-xs mt-8 text-[gray]">Collaborate and share knowledge with a private group for FREE.</h6>
            <h6 className="text-[blue] text-xs">Get Stack Overflow for Teams free for up to 50 users.</h6>

        </div>


        <div className="flex flex-col justify-center items-center min-h-screen">
        <div className="w-[300px] h-15 bg-[#FFFFFF] rounded-xl  left-2.5 border-2 border-[#E6E6E6] flex items-center mt-4 mb-2">
        <img className="w-10 h-10 mx-5" src={googleIcon} alt=" 구글 로그인 " />
        <div className="flex flex-col items-start">
          <GoogleLogin isLogin={isLogin} setIsLogin={setIsLogin}/>
        </div>
      </div>
        <div className="w-[300px] h-15 bg-[#FCEC4F] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center">
            <img className="w-10 h-10 mx-5" src={kakaoIcon} alt=" 카카오 로그인" />
            <KakaoLogin isLogin={isLogin} setIsLogin={setIsLogin} />
        </div>
        <GoogleUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>
        <KakaoUserInfo isLogin={isLogin} setIsLogin={setIsLogin}/>
        <div className="w-[300px] h-[350px] rounded-xl  left-2.5  border-2 border-[#E6E6E6] flex items-center justify-center mt-4 md-4 bg-[white]">
        <SignIn isLogin={isLogin} setIsLogin={setIsLogin}/>
        </div>
        </div>
        </div>
        </>
    )
}

export default SignUpPage