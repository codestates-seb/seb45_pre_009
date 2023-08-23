import React from "react";


const DropdownInbox = () => {
  return (
    <div className="absolute top-3/4 w-[375px] h-[423px] bg-white border border-solid border-gray-200 shadow right-0 mt-3 z-10 m-1.5">
      <div className="hover:bg-gray-100 w-full h-[32px] bg-zinc-100 border-b-gray-400 border-solid border justify-between flex text-[11px] p-[5px] ">
        <div className="font-bold text-gray-600 ">INBOX (ALL)</div>
        <span className="text-[12px] text-sky-700 hover:text-sky-500">Mark all as read</span>
      </div>
      <div className="pb-2.5 border border-solid border-b-gray-400 text-left hover:bg-gray-200">
        <div className="flex pt-2.5 text-[13px] text-gray-600">
          <img className="w-[16px] h-[15px]" src="Stack_Overflow_icon.png" />
          welcome
        </div>
        <div className="text-sky-700 font-bold text-[12px] pl-3.5">
          Welcome to Stack Overflow! Take the 2-minute site tour to earn your
          first badge.
        </div>
      </div>
      <div className="flex relative top-[280px] text-sky-700 justify-center text-[13px] hover:bg-gray-200">Go to full inbox</div>
    </div>
  );
};

export default DropdownInbox;
