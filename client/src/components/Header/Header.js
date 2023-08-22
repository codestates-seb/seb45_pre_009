
import React, { useState } from "react";
import "./Header.css";
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
    <div className="header">
      <a href="/"><img
        className="header_logo"
        src="https://velog.velcdn.com/images/devfrank9/post/1df259b2-e4cb-473b-8bcd-0388761a596a/image.png"
        alt="메인로고"
      />
        </a>

      <button
        className="header_navigation"
        type="button"
        onClick={() => setIsOpen({ ...isOpen, products: !isOpen.products })}
      >
        {" "}
        Products
        {isOpen.products ? <DropdownProducts /> : null}
      </button>

      <Search />
      <div className="header_nav">
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
            <button
              onClick={() => setIsOpen({ ...isOpen, Inbox: !isOpen.Inbox })}
            >
              <FontAwesomeIcon icon={faInbox} />
              {isOpen.Inbox ? <DropdownInbox /> : null}
            </button>
            <button
              onClick={() => setIsOpen({ ...isOpen, trophy: !isOpen.trophy })}
            >
              <FontAwesomeIcon icon={faTrophy} />
              {isOpen.trophy ? <Dropdowntrophy /> : null}
            </button>
            <button
              onClick={() =>
                setIsOpen({ ...isOpen, Question: !isOpen.Question })
              }
            >
              <FontAwesomeIcon icon={faCircleQuestion} />
              {isOpen.Question ? <DropdownQuestion /> : null}
            </button>
            <button
              onClick={() => setIsOpen({ ...isOpen, Bars: !isOpen.Bars })}
            >
              <FontAwesomeIcon icon={faBars} />
              {isOpen.Bars ? <DropdownBars /> : null}
            </button>
          </>
        )}
      </div>
    </div>
  );

}

export default Header;
