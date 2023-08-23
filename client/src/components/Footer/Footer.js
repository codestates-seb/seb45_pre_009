import React from 'react';


function Footer() {
    return (
        <div className="relative h-[322px] w-full bg-neutral-800 p-[40px] flex bottom-0 w-full justify-start text-white z-[800]" >
            <div className="w-[100px] h-[258px]">

            <img className="w-[85px] h-[35px]" src="Stack_Overflow_icon.png" alt='' />

            </div>

                <div className="flex flex-col basis-2/5 pl-9 w-[174px] h-[278px] ">
                <span className="pb-3 text-gray-300 font-bold text-[13px]">STACK OVERFLOW</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Question</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Help</span>
                </div>
                <div className="flex flex-col basis-2/6 w-[127px] h-[278px]">
                <span className="pb-3 text-gray-300 font-bold text-[13px]">PRODUCTS</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Teams</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Advertising</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Collectives</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Talent</span>
                </div>
                <div className="flex flex-col basis-2/5 w-[155px] h-[278px]">
                <span className="pb-3 text-gray-300 font-bold text-[13px]">COMPANY</span> 
                   <span className="text-gray-400 pb-1 text-[13px]">About</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Press</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Work Here</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Legal</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Privacy Policy</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Terms of Service</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Contact Us</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Cookie Settings</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Cookie Policy</span>
                </div>
                <div className="flex flex-col basis-3/5 w-[245px] h-[278px]">
                <span className="pb-3 text-gray-300 font-bold text-[13px]">STACK EXCHANGE NETWORK</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Techonology</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Culture & recreation</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Life & arts</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Science</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Professional</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Business</span>
                   <span className="text-gray-400 pb-1 text-[13px]"> </span>
                   <span className="text-gray-400 pb-1 text-[13px]">API</span>
                   <span className="text-gray-400 pb-1 text-[13px]">Data</span>
                </div>
                <div className="text-[11px] basis-3/5 flex flex-col justify-between h-[250px] text-gray-400">
                    <ul className="flist-none">
                        <li className="inline-block mr-3">Blog</li>
                        <li className="inline-block mr-3">Facebook</li>
                        <li className="inline-block mr-3">Twiiter</li>
                        <li className="inline-block mr-3">Linkedln</li>
                        <li className="inline-block mr-3">Instagram</li>
                    </ul>
                    <div className="flex flex-col top-10">
                    <p >Site design / logo © 2023 Stack Exchange Inc; user contributions licensed under <span className="underline"> CC BY-SA. </span> </p> 
                    <span>rev 2023.8.10.43574</span>
                    </div>
                </div>
            
        </div>
    );
}

export default Footer;