import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; 
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';

import Search from '../Header/Search';
import { Link } from "react-router-dom";


const DropdownBars = ({setIsLogin}) => {
  const handleLogout = () =>{
    setIsLogin(false)
    sessionStorage.setItem("isLogin",false);
    localStorage.setItem("jwt","");
    localStorage.setItem("refresh_token","");
    
}
  return (
    <div className="absolute top-3/4 w-[375px] h-[390px] bg-white border border-solid border-slate-100 right-0 z-10 m-[5px] mt-2.5 shadow">
      

        <div className="w-full h-[32px] bg-neutral-100 text-sky-700 justify-between flex text-[11px] p-2 font-bold">CURRENT COMMUNITY</div>
         <div className="flex bg-sky-50 justify-between">
          <div className="felx p-2.5 text-[13px] font-bold text-sky-700"><img className="w-[16px] h-[15px]" src="Stack_Overflow_icon.png" /> Stack Overflow</div>
          <button className="text-[13px] font-medium text-sky-600 hover:text-sky-800">help</button> 
          <button className="text-[13px] font-medium text-sky-600 hover:text-sky-800">chat</button> 
          <button className="text-[13px] font-medium text-sky-600 hover:text-sky-800" onClick={handleLogout}><a href="/">log out</a></button>

          </div>
          <div className="flex text-sky-600 bg-sky-50 text-[13px] pl-3.5 p-2.5 items-center hover:bg-sky-100">┕<img className="w-[16px] h-[15px] text-black" src="Stack.png" />
          Meta Stack Overflow</div>
        
      
      <div>
        <div className="w-full h-[32px] bg-neutral-100 text-sky-700 justify-between flex text-[11px] p-2 font-bold">YOUR COMMUTNITIES
        <span className="text-[13px] font-medium text-sky-600 hover:text-sky-800">edit</span></div>
        <div className="text-[13px] flex p-2.5 text-sky-600 hover:bg-sky-100">
          <img className="w-[16px] h-[15px]" src="Stack_Overflow_icon.png" />
          Stack Overflow
        </div>
      </div>
      <div>
        <div className="w-full h-[32px] bg-neutral-100 text-sky-700 justify-between flex text-[11px] p-2 font-bold">
            <div>MORE STACK EXCHANGE COMMUNITY</div>
            <span className="text-[13px] font-medium text-sky-600 hover:text-sky-800">company blog</span>
        </div>
        <div className="flex m-2.5 items-start border border-gray-200 border-solid rounded p-px">
          <FontAwesomeIcon icon={faMagnifyingGlass} className="text-gray-500"/>
          <input className="border-0 h-[30px] w-full text-[13px] p-[5px] focus:outline-1 focus:ring focus:outline-cyan-50 " type="text" placeholder="Find a Stack Exchange community" />
        </div>
      </div>
    </div>
  );
};

export default DropdownBars;
