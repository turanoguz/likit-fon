import React from "react";
import Header from "./Header";
import LeftSideBar from "./LeftSideBar";
import "./UserLayout.css";
function UserLayout({ headerText, children }) {
  return (
    <>
      <Header headerText={headerText} />
      <div className="flex flex-nowrap text-white relative">
        <LeftSideBar />
        <div
          style={{
            background:
              "linear-gradient(rgb(158, 154, 154) 0%, rgb(255 255 255 / 98%) 100%)",
            boxShadow: "inset 0px 4px 4px rgba(0, 0, 0, 0.25)",
            zIndex: "0",
          }}
          className="content h-screen"
        >
          {children}
        </div>
      </div>
    </>
  );
}

export default UserLayout;
