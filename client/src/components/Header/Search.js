
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'; 
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import Searchbox from './Searchbox';
import { useState } from 'react';

function Search() {
    const [view, setView] = useState(false);
   
    const handleDropdown = (isVisible) => {
        setView(isVisible);
    };

    return (
        <div className="flex flex-1 items-center border-[1px] border-gray-400 border-solid rounded-[5px] pl-2.5 relative ">
           <FontAwesomeIcon icon={faMagnifyingGlass} className="text-gray-500"/>
           <input className="h-[30px] w-full border-none p-2 text-[14px] focus:outline-1 focus:ring focus:outline-cyan-50" type="text" placeholder="Search..." 
           onFocus={()=>{handleDropdown(true)}} onBlur={()=>{handleDropdown(false)}}></input>
            {view ? <Searchbox /> : null}
        </div>
    );
}

export default Search;