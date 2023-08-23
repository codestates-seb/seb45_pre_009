import React from "react";


const DropdownProducts = () => {
  return (
    <div className="absolute top-[80%] left-[80px] w-[210px] h-[377px] bg-white border border-solid border-gray-200 p-[5px] z-10 m-2 shadow rounded-md text-left">
      <div className="bg-white p-[5px] flex flex-col">
        <div className="pb-3 hover:bg-gray-300 rounded">
          <div className="text-[13px] text-black">Stack Overflow</div>
          <div className="text-[12px] text-gray-700">Public questions & answers</div>
        </div>
        <div className="pb-3 hover:bg-gray-300 rounded">
          <div className="text-[13px] text-black">Stack Overflow for Teams</div>
          <div className="text-[12px] text-gray-600">
            Where developers & techonolgists share private knowledge with
            coworkers
          </div>
        </div>
        <div className="pb-3 hover:bg-gray-300 rounded">
          <div className="text-[13px] text-black">Talent</div>
          <div className="text-[12px] text-gray-600">Build your employer brand</div>
        </div>
        <div className="pb-3 hover:bg-gray-300 rounded">
          <div className="text-[13px] text-black">Advertising</div>
          <div className="text-[12px] text-gray-600">Reach developers & techonologists worldwide</div>
        </div>
      </div>
      <div className="p-[5px] border-t-gray-200 border-solid border pb-2 pt-2 hover:bg-gray-300 rounded">
        <div className="text-[13px] text-black">Labs</div>
        <div className="text-[12px] text-gray-600">The future of Collective knowledge sharing</div>
      </div>
      <div className="p-[5px] pt-2 border-t-gray-200 border-solid border bg-gray-100 text-gray-500 text-[13px] hover:text-black">About the company</div>
    </div>
  );
};

export default DropdownProducts;
