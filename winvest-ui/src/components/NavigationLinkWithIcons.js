import React, { Component } from "react";
import { LiaArrowRightSolid } from "react-icons/lia";
import NavigationDropdown from "./NavigationDropdown";
import { findDOMNode } from "react-dom";
import "./NavigationLinkWithIcons.css";

export default class NavigationLinkWithIcons extends Component {
  constructor(props) {
    super(props);
    this.state = { active: false };
    this.onClick = this.onClick.bind(this);
    this.onClose = this.onClose.bind(this);
  }

  onClick() {
    this.setState({ active: !this.state.active });
  }

  onClose(event) {
    const domNode = findDOMNode(this);
    if (!domNode || !domNode.contains(event.target))
      this.setState({ active: false });
  }

  componentDidMount() {
    document.addEventListener("click", this.onClose, true);
  }

  componentWillUnmount() {
    document.removeEventListener("click", this.onClose, true);
  }

  render() {
    const { icon, title, modalRoutes, customNotificationValue, inPage } =
      this.props;
    return (
      <div
        onClick={this.onClick}
        className={`nav-button flex ${
          inPage
            ? "bg-[#323131]"
            : this.state.active
            ? "bg-[#9c060d]"
            : "bg-[#f93d47]"
        } drop-shadow-custom rounded px-4 py-2`}
      >
        <button className={"flex items-center justify-between gap-x-4 w-full"}>
          <span className="flex flex-row gap-x-2 items-center">
            {icon && <span>{icon}</span>}
            <span>{title}</span>
          </span>
          <span style={{ lineHeight: "100%" }}>
            {modalRoutes && (
              <span className={"z-10"}>
                <NavigationDropdown
                  routes={modalRoutes}
                  button={<LiaArrowRightSolid size={28} />}
                  parentActive={this.state.active}
                />
              </span>
            )}
            {!this.props.modalRoutes && customNotificationValue && (
              <span
                className={
                  "bg-white text-[#EC323B] rounded py-0.5 px-2 block items-center m-0"
                }
              >
                {customNotificationValue}
              </span>
            )}
          </span>
        </button>
      </div>
    );
  }
}
