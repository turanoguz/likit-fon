import React from "react";
import { Link, NavLink } from "react-router-dom";
import { GoHomeFill } from "react-icons/go";
import NavigationLinkWithIcons from "../../components/NavigationLinkWithIcons";
import { BsFillChatDotsFill, BsFillPeopleFill } from "react-icons/bs";
import { PiNotePencilFill } from "react-icons/pi";
import { IoMdSettings } from "react-icons/io";
import { CgNotes } from "react-icons/cg";
import { IoExit } from "react-icons/io5";
import logo from "../../assets/img/logo.png";
import CurrencyExchangeIcon from "@mui/icons-material/CurrencyExchange";

const navRoutes = {
  likitfon: [
    { name: "Fon Tanımlama", route: "/fund/create" },
    { name: "Fon Alım - Satım", route: "/fund/trade" },
    { name: "Fon Detay", route: "/fund/details" },
    { name: "Fon Gün Sonu Değer", route: "/fund/endofday" },
  ],
  customerSettings: [
    { name: "Müşteri Ekle / Çıkar", route: "/clients/edit" },
    { name: "Müşteri Güncelleme", route: "/clients/update" },
    { name: "Müşteriler", route: "/clients" },
  ],
  cashSettings: [
    { name: "Nakit Ekle", route: "/cash/add" },
    { name: "Havale İşlemi", route: "/cash/transfer" },
  ],
};

function LeftSidebar({}) {
  const pathName = window.location.pathname;
  return (
    <nav
      style={{ zIndex: "1" }}
      className="flex flex-col h-screen fixed top-0 "
    >
      <div className="p-0 m-0 bg-[#323131] flex items-center justify-center winvest-logo-wrapper">
        <Link to={"/"}>
          <img className="winvest-logo" alt="winvest logo" src={logo}></img>
        </Link>
      </div>
      <div
        style={{
          backgroundColor: "#EC323B",
          boxShadow: "0px 9.38461px 93.8461px rgba(0, 0, 0, 0.15)",
        }}
        className="flex p-5 flex-col h-full"
      >
        <ul className={"p-4 space-y-4"}>
          <li>
            <NavLink to={"/"}>
              <NavigationLinkWithIcons
                icon={<GoHomeFill size={28} />}
                title={"Genel Özet"}
                inPage={pathName.length === 1}
              />
            </NavLink>
          </li>
          <li>
            <NavigationLinkWithIcons
              icon={<PiNotePencilFill size={28} />}
              title={"Likit Fon İşlemleri"}
              modalRoutes={navRoutes.likitfon}
              inPage={pathName.startsWith("/fund")}
            />
          </li>
          <li>
            <NavigationLinkWithIcons
              icon={<BsFillPeopleFill size={28} />}
              title={"Müşteri İşlemleri"}
              modalRoutes={navRoutes.customerSettings}
              inPage={pathName.startsWith("/clients")}
            />
          </li>
          <li>
            <NavigationLinkWithIcons
              icon={<CurrencyExchangeIcon size={28} />}
              title={"Nakit İşlemleri"}
              modalRoutes={navRoutes.cashSettings}
              inPage={pathName.startsWith("/cash")}
            />
          </li>
          <li>
            <NavLink to={"/admin"}>
              <NavigationLinkWithIcons
                icon={<IoMdSettings size={28} />}
                title={"Yetkili İşlemleri"}
                inPage={pathName.startsWith("/admin")}
              />
            </NavLink>
          </li>
          <li>
            <NavLink to={"/support"}>
              <NavigationLinkWithIcons
                icon={<BsFillChatDotsFill size={28} />}
                title={"Destek"}
                isModal={false}
                customNotificationValue={2}
              />
            </NavLink>
          </li>
        </ul>
        <ul className={"p-4 space-y-4 mt-auto"}>
          <li className="bg-[#323131] rounded px-4 py-2">
            <Link
              to={"/logout"}
              className={"flex flex-row gap-x-2 items-center"}
            >
              <span>
                <IoExit size={28} />
              </span>
              <span>Güvenli Çıkış</span>
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default LeftSidebar;
