import React from "react";
import "./Bars.css";
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
    <div className="bars_container">
      
        <div className="bars_header">CURRENT COMMUNITY</div>
         <div className="bars_box">
          <div className="bars_header2"><img className="bars_img" src="Stack_Overflow_icon.png" /> Stack Overflow</div>
          <button className="bars_ect">help</button> 
          <button className="bars_ect">chat</button> 
          <button className="bars_ect" onClick={handleLogout}><a href="/">log out</a></button>
          </div>
          <div className="bars_content">┕<img className="bars_img2" src="Stack.png" />
          Meta Stack Overflow</div>
        
      
      <div>
        <div className="bars_header">YOUR COMMUTNITIES
        <span className="bars_ect">edit</span></div>
        <div className="bars_content2">
          <img className="bars_img" src="Stack_Overflow_icon.png" />
          Stack Overflow
        </div>
      </div>
      <div>
        <div className="bars_header">
            <div>MORE STACK EXCHANGE COMMUNITY</div>
            <span className="bars_ect">company blog</span>
        </div>
        <div className="bars_search">
          <FontAwesomeIcon icon={faMagnifyingGlass} className="search_icon"/>
          <input className="bars_input" type="text" placeholder="Find a Stack Exchange" />
        </div>
      </div>
    </div>
  );
};

export default DropdownBars;
