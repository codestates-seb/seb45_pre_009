import React from "react";
import "./Question.css";

const DropdownQuestion = () => {
  return (
    <div className="question_container">
        <div className="question_tour">
            <div className="question_header">Tour</div>
            <span>Start here for a quick overview of the site</span>
        </div>
        <div className="question_help">
            <div className="question_header">Help Center</div>
            <span>Detailed answers to any questions you might have</span>
        </div>
        <div className="question_meta">
            <div className="question_header">Meta</div>
            <span>Discuss the workings and policies of this site</span>
        </div>
        <div className="question_about">
            <div className="question_header">About Us</div>
            <span>Learn mor about Stack Overflow the company, and our products.</span>
        </div>
    </div>
  );
};

export default DropdownQuestion;
