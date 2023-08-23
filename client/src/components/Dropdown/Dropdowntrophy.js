import React from "react";


const Dropdowntrophy = () => {
  return (
    
      <div className="absolute top-[78%] w-[375px] h-[422px] bg-white border-gray-200 border-solid border shadow right-0 mt-3 z-10 m-[5px]">
        <div className="w-full h-[32px] bg-gray-100 justify-between flex text-[11px] p-[5px]">
            <div className="font-bold text-gray-600">ACHIEVEMENTS <span className="text-[11px] font-light pl-0.5">UTC TIME 07:08</span></div>
            
            <span className="text-[12px] text-sky-700">privileges ・ badge</span>
        </div>
        <div className="text-left text-[12px] p-[4px] text-gray-800">
            <div className="pb-2">You have not yet earnet any achievements.</div>
            <div>Why not <span className="underline text-sky-700">take the tour</span>or<span className="underline text-sky-700">fill out your profile</span>?</div>
        </div>
      </div>
  );
};

export default Dropdowntrophy;