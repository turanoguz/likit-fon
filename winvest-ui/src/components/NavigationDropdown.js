import { Menu } from "@headlessui/react";
import { Link } from "react-router-dom";

export default function NavigationDropdown({ routes, button, parentActive }) {
  return (
    <Menu as="nav" className={"relative"}>
      {button}
      {parentActive && (
        <Menu.Items
          static
          className={
            "absolute top-[-20px] left-[50px] bg-active rounded translate-y-2 p-1 w-48 bg-white bg-opacity-20"
          }
        >
          {routes.map((item, index) => (
            <Menu.Item key={index}>
              {({ active }) => (
                <Link
                  className={`h-8 px-4 mb-1 flex justify-between list-disc items-center text-sm bg-[#323131] text-white rounded ${
                    active && "bg-white bg-opacity-10"
                  }`}
                  to={item.route}
                >
                  {item.name}
                </Link>
              )}
            </Menu.Item>
          ))}
        </Menu.Items>
      )}
    </Menu>
  );
}
