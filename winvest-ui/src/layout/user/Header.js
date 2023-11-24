import React from "react";
import { Link } from "react-router-dom";
import { GoOrganization } from "react-icons/go";
import { BsCalendar3 } from "react-icons/bs";
import { AiOutlineUser } from "react-icons/ai";
import "./Header.css";

function Header({ headerText }) {
  let today = new Date().toLocaleDateString("tr-TR", {
    day: "numeric",
    weekday: "long",
    year: "numeric",
    month: "short",
  });
  return (
    <div className={"bg-[#323131] flex px-2 py-5"}>
      <div className="header-text">{headerText}</div>
      <div className="flex justify-end w-full">
        <nav className="items-center justify-center flex mr-6">
          <ul className={"flex flex-row flex-nowrap whitespace-nowrap gap-10"}>
            <li>
              <Link
                to={"/profile"}
                className="flex gap-2 items-center truncate"
              >
                <AiOutlineUser size={44} color={"white"} />
                <span className={"text-white text-xl font-normal"}>
                  İrfan DUMAN
                </span>
              </Link>
            </li>
            <li className="flex gap-2 items-center">
              <BsCalendar3 size={44} color={"white"} />
              <span className={"text-white ml-2 text-xl font-normal"}>
                {today}
              </span>
            </li>
            <li className="flex gap-2 items-center">
              <GoOrganization size={44} color={"white"} />
              <span className={"text-white text-xl ml-1 font-normal"}>
                WinVest A.Ş.
              </span>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}

export default Header;
