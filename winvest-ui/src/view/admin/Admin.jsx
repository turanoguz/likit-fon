import React, { useState } from "react";
import "./Admin.css";
import { ToastContainer } from "react-toastify";
import { Button } from "@mui/material";
import UserLayout from "../../layout/user/UserLayout";
import AddCompany from "./AddCompany";
import AddWorker from "./AddWorker";
import AddBranch from "./AddBranch";
import AddRole from "./AddRole";
import AddPrivileges from "./AddPrivileges";
import AddPrivilegesRole from "./AddPrivilegesRole";
import AddRoleUser from "./AddRoleUser";
export default function Admin() {
  const [componentIndex, setComponentIndex] = useState(0);
  const [component, setComponent] = useState(<AddCompany></AddCompany>);
  return (
    <UserLayout headerText={"YETKİLİ İŞLEMLERİ"}>
      <div className="admin-wrapper">
        <div className="admin-buttons">
          <Button
            onClick={() => {
              setComponentIndex(0);
              setComponent(<AddCompany />);
            }}
            className={`admin-button ${componentIndex == 0 ? "active" : ""}`}
            variant="contained"
          >
            ŞİRKET EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(1);
              setComponent(<AddBranch />);
            }}
            className={`admin-button ${componentIndex == 1 ? "active" : ""}`}
            variant="contained"
          >
            ŞUBE EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(2);
              setComponent(<AddWorker />);
            }}
            className={`admin-button ${componentIndex == 2 ? "active" : ""}`}
            variant="contained"
          >
            ÇALIŞAN EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(3);
              setComponent(<AddRole />);
            }}
            className={`admin-button ${componentIndex == 3 ? "active" : ""}`}
            variant="contained"
          >
            ROL EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(4);
              setComponent(<AddPrivileges />);
            }}
            className={`admin-button ${componentIndex == 4 ? "active" : ""}`}
            variant="contained"
          >
            YETKİ EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(5);
              setComponent(<AddPrivilegesRole />);
            }}
            className={`admin-button ${componentIndex == 5 ? "active" : ""}`}
            variant="contained"
          >
            ROLE YETKİ EKLE
          </Button>
          <Button
            onClick={() => {
              setComponentIndex(6);
              setComponent(<AddRoleUser />);
            }}
            className={`admin-button ${componentIndex == 6 ? "active" : ""}`}
            variant="contained"
          >
            ÇALIŞANA ROL EKLE
          </Button>
        </div>
        {component}
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
