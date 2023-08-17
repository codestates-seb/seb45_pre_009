import React from 'react';
import './Header.css'
import Search from './Search';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faInbox, faTrophy, faCircleQuestion, faBars } from '@fortawesome/free-solid-svg-icons';
import { Link } from "react-router-dom";


function Header({isLogin,setIsLogin}) {
    // const handleGoLogin = () => {

    // }

    return (
        <div className="header">
            
            <img className="header_logo" src="https://velog.velcdn.com/images/devfrank9/post/1df259b2-e4cb-473b-8bcd-0388761a596a/image.png" />
            
            
            <button className="header_navigation" type="button">Products</button>
            <Search />
            <div className="header_nav">
                {isLogin?null:<button className='mr-4'>log in</button>}
                {isLogin?null:<button>Sign up</button>}
                {isLogin?<FontAwesomeIcon icon={faInbox} className="fainbox"/>:null}
                 {isLogin?<FontAwesomeIcon icon={faTrophy} className="fatrophy"/>:null}
                 {isLogin?<FontAwesomeIcon icon={faCircleQuestion} className="question"/>:null}
                 {isLogin?<FontAwesomeIcon icon={faBars} className="fabars"/>:null}
            </div>
        </div>
    );
}

export default Header;