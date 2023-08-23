import React from "react";


const DropdownQuestion = () => {
  return (
    <div className="absolute top-[88%] w-[215px] h-[260px] bg-white  border-gray-200 border-solid border shadow right-0 mt-3 z-10 m-[5px] text-left text-[12px]">
        <div className="p-[2px] border-t-gray-100 border-solid border hover:bg-gray-200 ">
            <div className="text-sky-700">Tour</div>
            <span>Start here for a quick overview of the site</span>
        </div>
        <div className="p-[2px] border-b-gray-100 border-solid border w-[213px] h-[67px] hover:bg-gray-200">
            <div className="text-sky-700">Help Center</div>
            <span>Detailed answers to any questions you might have</span>
        </div>
        <div className="p-[2px] border-b-gray-100 border-solid border w-[213px] h-[67px] hover:bg-gray-200">
            <div className="text-sky-700">Meta</div>
            <span>Discuss the workings and policies of this site</span>
        </div>
        <div className="p-[2px] border-b-gray-100 border-solid border w-[213px] h-[66px] hover:bg-gray-200">
            <div className="text-sky-700">About Us</div>
            <span>Learn mor about Stack Overflow the company, and our products.</span>
        </div>
    </div>
  );
};

export default DropdownQuestion;
