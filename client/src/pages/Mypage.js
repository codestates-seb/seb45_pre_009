import axios from "axios";
import Activity from "../components/Mypage/Activity";
import Profile from "../components/Mypage/Profile";
import Settings from "../components/Mypage/Settings";
import Saves from "../components/Mypage/Saves";
import { useEffect, useState } from "react";
import profile from "../images/profile.png"
import pfEdit from "../images/pfEdit.png";
import nwEdit from "../images/nwEdit.png";

const Mypage = () => {

    const [mypageName,setMypageName] = useState("");
    const [mypageEmail,setMypageEmail] = useState("");

    const memberId = localStorage.getItem("memberId");



    useEffect(()=>{
        axios.get(`http://3.39.55.166:8080/members/${memberId}`,{})
            .then(res=>{
                // setEmail(res.)
                console.log("유저정보",res);
                setMypageEmail(res.data.email);
                setMypageName(res.data.displayName);
            }).catch(err=>{
                console.log(err);
            })
    },[memberId])

    return (
        <>
        <div className="flex w-full mt-10 ml-10 mr-10 md-10 h-[145px]">
            <div className="flex-none">
                <img className="w-[128px] h-[128px] rounded-lg mx-2 my-2" src={profile} alt="프로필사진"></img>
            </div>
            <div className="ml-10 flex-grow">
                {mypageName}
            </div>
            <div className="ml-10 flex-none flex justify-center ">
                <button className="rounded-lg mr-3 border border-gray-400 border-1 w-[100px] h-[33px] p-[10px] text-xs">
                    {/* <img className="w-[15px] h-[15px]" src={pfEdit} alt="프로필편집"></img> */}
                    <span className="">Edit profile</span>
                </button>
                <button className="rounded-lg mr-3 border border-gray-400 border-1 w-[130px] h-[33px] p-[10px] text-xs">
                    {/* <img className="w-[15px] h-[15px]" src={nwEdit} alt="네트워크편집"></img> */}
                    <span>Network profile</span>
                </button>
            </div>
        </div>

        <Activity/>
        <Profile/>
        <Saves/>
        <Settings/>
        </>
    )
}

export default Mypage;