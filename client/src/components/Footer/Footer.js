import React from 'react';
import './Footer.css';

function Footer() {
    return (
        <div className="footer_container">
            <img className="footer_logo" src="Stack_Overflow_icon.png" />
            <ul className="footer_nav">
                <li className="footer_col">STACK OVERFLOW</li>
                   <span>Question</span>
                   <span>Help</span>
                <li className="footer_col2">PRODUCTS</li>
                   <span>Teams</span>
                   <span>Advertising</span>
                   <span>Collectives</span>
                   <sapn>Talent</sapn>
                <li className="footer_col3">COMPANY</li>
                   <span>About</span>
                   <span>Press</span>
                   <sapn>Work Here</sapn>
                   <sapn>Legal</sapn>
                   <span>Privacy Policy</span>
                   <span>Terms of Service</span>
                   <span>Contact Us</span>
                   <span>Cookie Settings</span>
                   <span>Cookie Policy</span>
            </ul>
        </div>
    );
}

export default Footer;