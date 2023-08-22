import React from "react";
import "./trophy.css";

const Dropdowntrophy = () => {
  return (
    
      <div className="trophy_container">
        <div className="trophy_header">
            <div className="trophy_achievements">ACHIEVEMENTS <span className="trophy_time">UTC TIME 07:08</span></div>
            
            <span className="trophy_side">privileges ・ badge</span>
        </div>
        <div className="trophy_box">
            <div className="trophy_explain">You have not yet earnet any achievements.</div>
            <div>Why not <span className="trophy_under">take the tour</span>or<span className="trophy_under">fill out your profile</span>?</div>
        </div>
      </div>
  );
};

export default Dropdowntrophy;