import React from "react";
import "./Inbox.css";

const DropdownInbox = () => {
  return (
    <div className="Inbox_container">
      <div className="Inbox_header">
        <div className="Inbox_drop">INBOX (ALL)</div>
        <span className="Inbox_mark">Mark all as read</span>
      </div>
      <div className="Inbox_alarm">
        <div className="inbox_welcome">
          <img className="inbox_img" src="Stack_Overflow_icon.png" />
          welcome
        </div>
        <div className="inbox_welcome2">
          Welcome to Stack Overflow! Take the 2-minute site tour to earn your
          first badge.
        </div>
      </div>
      <div className="inbox_bottom">Go to full inbox</div>
    </div>
  );
};

export default DropdownInbox;
