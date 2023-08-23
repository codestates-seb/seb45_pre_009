
import React, { useState } from "react";

import Search from "./Search";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faInbox,
  faTrophy,
  faCircleQuestion,
  faBars,
} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";
import DropdownProducts from "../Dropdown/DropdownProducts";
import DropdownBars from "../Dropdown/DropdownBars";
import DropdownInbox from "../Dropdown/DropdownInbox";
import DropdownQuestion from "../Dropdown/DropdownQuestion";
import Dropdowntrophy from "../Dropdown/Dropdowntrophy";


function Header({ isLogin, setIsLogin }) {
  // const handleGoLogin = () => {

  // }
  const [isOpen, setIsOpen] = useState({
    products: false,
    Inbox: false,
    Bars: false,
    trophy: false,
    Question: false,
  });

  return (

    <div className="h-[56px] flex items-center bg-white border-t-orange-400 border-t-[3px] border-solid border-b-zinc-300 border-b-[1px] sticky top-0 z-[1000]">
      <a href="/"><img
        className="w-[166px] h-[52px] object-contain"

        src="https://velog.velcdn.com/images/devfrank9/post/1df259b2-e4cb-473b-8bcd-0388761a596a/image.png"
        alt="메인로고"
      />
        </a>

      <button
        className="text-[13px] rounded-[20px] bg-white border-0 border-white none top-[50%] text-slate-600 p-[4px] pr-5"
        type="button"
        onClick={() => setIsOpen({ ...isOpen, products: !isOpen.products })}
      >
        {" "}
        Products
        {isOpen.products ? <DropdownProducts /> : null}
      </button>

      <Search />
      <div className="flex justify-evenly text-[18px] text-gray-600 mx-2.5">
        {!isLogin ? (
          <>
            <button className="mr-4">
              <a href="/login">log in</a>
            </button>
            <button>
              <a href="/signup">Sign up</a>
            </button>
          </>
        ) : (
          <>
            <a href="/Mypage">
            <button><img className="w-[25px] h-[25px] rounded-[3px] m-2" src="images.jpeg" alt='' /></button>
            </a>
            <button className="p-2.5 hover:bg-gray-300"
              onClick={() => setIsOpen({ ...isOpen, Inbox: !isOpen.Inbox })}
            >
              <FontAwesomeIcon icon={faInbox} />
              {isOpen.Inbox ? <DropdownInbox /> : null}
            </button>
            <button className="p-2.5 hover:bg-gray-300"
              onClick={() => setIsOpen({ ...isOpen, trophy: !isOpen.trophy })}
            >
              <FontAwesomeIcon icon={faTrophy} />
              {isOpen.trophy ? <Dropdowntrophy /> : null}
            </button>
            <button className="p-2.5 hover:bg-gray-300"
              onClick={() =>
                setIsOpen({ ...isOpen, Question: !isOpen.Question })
              }
            >
              <FontAwesomeIcon icon={faCircleQuestion} />
              {isOpen.Question ? <DropdownQuestion /> : null}
            </button>
            <button className="p-2.5 hover:bg-gray-300"
              onClick={() => setIsOpen({ ...isOpen, Bars: !isOpen.Bars })}
            >
              <FontAwesomeIcon icon={faBars} />
              {isOpen.Bars ? <DropdownBars setIsLogin={setIsLogin}/> : null}
            </button>
          </>
        )}
      </div>
    </div>
  );

}

export default Header;
