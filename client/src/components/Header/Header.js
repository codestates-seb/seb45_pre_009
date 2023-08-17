import React from 'react';
import './Header.css'
import Search from './Search';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInbox, faTrophy, faCircleQuestion, faBars } from '@fortawesome/free-solid-svg-icons';


function Header() {
    return (
        <div className="header">
            
            <img className="header_logo" src="https://velog.velcdn.com/images/devfrank9/post/1df259b2-e4cb-473b-8bcd-0388761a596a/image.png" />
            
            
            <button className="header_navigation" type="button">Products</button>
            <Search />
            <div className="header_nav">
                 <FontAwesomeIcon icon={faInbox} className="fainbox"/>
                 <FontAwesomeIcon icon={faTrophy} className="fatrophy"/>
                 <FontAwesomeIcon icon={faCircleQuestion} className="question"/>
                 <FontAwesomeIcon icon={faBars} className="fabars"/>
            </div>
        </div>
    );
}

export default Header;