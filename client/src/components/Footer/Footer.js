import React from 'react';
import './Footer.css';

function Footer() {
    return (
        <div className="footer_container">
            <div className="footer_logobox">

            <img className="footer_logo" src="Stack_Overflow_icon.png" alt='' />

            </div>

                <div className="footer_box1">
                <span className="footer_title">STACK OVERFLOW</span>
                   <span className="footer_primary">Question</span>
                   <span className="footer_primary">Help</span>
                </div>
                <div className="footer_box2">
                <span className="footer_title">PRODUCTS</span>
                   <span className="footer_primary">Teams</span>
                   <span className="footer_primary">Advertising</span>
                   <span className="footer_primary">Collectives</span>
                   <span className="footer_primary">Talent</span>
                </div>
                <div className="footer_box3">
                <span className="footer_title">COMPANY</span> 
                   <span className="footer_primary">About</span>
                   <span className="footer_primary">Press</span>
                   <span className="footer_primary">Work Here</span>
                   <span className="footer_primary">Legal</span>
                   <span className="footer_primary">Privacy Policy</span>
                   <span className="footer_primary">Terms of Service</span>
                   <span className="footer_primary">Contact Us</span>
                   <span className="footer_primary">Cookie Settings</span>
                   <span className="footer_primary">Cookie Policy</span>
                </div>
                <div className="footer_box4">
                <span className="footer_title">STACK EXCHANGE NETWORK</span>
                   <span className="footer_primary">Techonology</span>
                   <span className="footer_primary">Culture & recreation</span>
                   <span className="footer_primary">Life & arts</span>
                   <span className="footer_primary">Science</span>
                   <span className="footer_primary">Professional</span>
                   <span className="footer_primary">Business</span>
                   <span className="footer_primary"> </span>
                   <span className="footer_primary">API</span>
                   <span className="footer_primary">Data</span>
                </div>
                <div className="footer_box5">
                    <ul className="footer_social">
                        <li className="footer_track">Blog</li>
                        <li className="footer_track">Facebook</li>
                        <li className="footer_track">Twiiter</li>
                        <li className="footer_track">Linkedln</li>
                        <li className="footer_track">Instagram</li>
                    </ul>
                    <div className="footer_bar">
                    <p className="footer_copyright">Site design / logo © 2023 Stack Exchange Inc; user contributions licensed under <span className="footer_link"> CC BY-SA. </span> </p> 
                    <span className="footer_ver">rev 2023.8.10.43574</span>
                    </div>
                </div>
            
        </div>
    );
}

export default Footer;