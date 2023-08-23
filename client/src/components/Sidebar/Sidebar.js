import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleInfo, faEarthAsia, faSquareVirus, faLock } from '@fortawesome/free-solid-svg-icons';


function Sidebar() {
    return (
        <div className="min-w-[164px] v640:hidden bg-white list-none w-[164px] sticky pt-4 h-[100vh] top-14 border-r border-solid border-e-gray-300">
          <div className="fixed">
               <div className="text-[13px] text-gray-600 pl-2 text-justify justify-between flex pb-3.5 pr-2 hitespace-nowrap  hover:text-gray-950"><a href='/'>Home</a></div>
               <div className="text-[13px] text-gray-600 pl-2 text-justify justify-between flex pb-3.5 pr-2 hitespace-nowrap">PUBLC</div>
                    <span className="text-gray-700 text-[13px] pl-2.5 pb-2.5  hover:text-gray-950"><FontAwesomeIcon icon={faEarthAsia} /><a href='/questions'>Questions</a></span>
               <div className="flex flex-col text-[13px] ps-6 text-gray-700">
                    <span className="pt-2.5  hover:text-gray-950">Tags</span>
                    <span className="pt-2.5  hover:text-gray-950"><a href='/userspage'>Users</a></span>
                    <span className="pt-2.5  hover:text-gray-950">Companies</span>
               </div>
               <div className="pt-5">
                    <span className="text-[13px] text-gray-600 pl-2 text-justify justify-between flex pb-3.5 pr-2 hitespace-nowrap">COOLECTIVES <FontAwesomeIcon icon={faCircleInfo} /></span>
                    <span className="text-[13px] text-gray-700 hover:text-gray-950 pl-1"><FontAwesomeIcon className="text-orange-400 text-[18px] pr-1" icon={faSquareVirus} />Explore Collectives</span>
               </div>
               <div className="pb-3 pt-5">
                    <span className="text-[13px] text-gray-600 pl-2 text-justify justify-between flex pb-3.5 pr-2 hitespace-nowrap">TEAMS <FontAwesomeIcon icon={faCircleInfo} /></span>
                    <span className="text-[13px] text-gray-700 hover:text-gray-950 pl-1"><FontAwesomeIcon className="text-orange-400 text-[18px] pr-1" icon={faLock} />Create free Team</span>
               </div>
               <button className="text-[12px] p-2 bg-sky-50 text-blue-800 rounded-md">Looking for your Teams?</button>
          </div>
        </div>
    );
}

export default Sidebar;