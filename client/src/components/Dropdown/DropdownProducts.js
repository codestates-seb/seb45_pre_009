import { useState } from 'react';
import React from 'react';
import './Header.css'
import Header from '../Header/Header';

function DropdownProducts() {
    const [view, setView] = useState(false);
   
    const handleDropdown = (isVisible) => {
        setView(isVisible);
    };

    return (
        <div>
           <button className="navigation_box"
           onFocus={()=>{handleDropdown(true)}} onBlur={()=>{handleDropdown(false)}}></button>
            {view ? <Header/> : null}
            <div>
                <ul>
                    <li>
                        <span>Stack Overflow</span>
                        <span>Public questions & answers</span>
                    </li>
                    <li>
                        <span>Stack Overflow for Teams</span>
                        <span>Where developers &
                            techonolgists share private
                            knowledge with coworkers
                        </span>
                    </li>
                </ul>
            </div>
        </div>
    );
}

export default DropdownProducts;