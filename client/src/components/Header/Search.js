import './Header.css'
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
        <div className="header_search">
           <FontAwesomeIcon icon={faMagnifyingGlass} className="search_icon"/>
           <input className="header_searchInput" type="text" placeholder="Search..." 
           onFocus={()=>{handleDropdown(true)}} onBlur={()=>{handleDropdown(false)}}></input>
            {view ? <Searchbox /> : null}
        </div>
    );
}

export default Search;