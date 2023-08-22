import React from 'react';
import './Sidebar.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCircleInfo, faEarthAsia, faSquareVirus, faLock } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

function Sidebar() {
    return (
        <div className="sidebar_container min-w-[164px] v640:hidden ">
          <div className='sidebar_container_sticky'>
               <div className="sidebar_title"><Link to='/'>Home</Link></div>
               <div className="sidebar_title">PUBLC</div>
                    <span className="sidebar_que"><FontAwesomeIcon icon={faEarthAsia} /><Link to='/questions'>Questions</Link></span>
               <div className="sidebar_nav">
                    <span className="nav_tags">Tags</span>
                    <span className="nav_users"><Link to='/userspage'>Users</Link></span>
                    <span className="nav_com">Companies</span>
               </div>
               <div className="sidebar_collectives">
                    <span className="sidebar_title">COOLECTIVES <FontAwesomeIcon icon={faCircleInfo} /></span>
                    <span className="sidebar_except"><FontAwesomeIcon className="icon" icon={faSquareVirus} />Explore Collectives</span>
               </div>
               <div className="sidebar_teams">
                    <span className="sidebar_title">TEAMS <FontAwesomeIcon icon={faCircleInfo} /></span>
                    <span className="sidebar_except"><FontAwesomeIcon className="icon" icon={faLock} />Create free Team</span>
               </div>
               <button className="sidebar_but">Looking for your Teams?</button>
          </div>
        </div>
    );
}

export default Sidebar;