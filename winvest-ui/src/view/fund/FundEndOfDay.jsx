import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { Button } from "@mui/material";
import "./FundEndOfDay.css";
import GetAppIcon from "@mui/icons-material/GetApp";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
export default function FundEndOfDay() {
  return (
    <UserLayout headerText={"Fon Gün Sonu"}>
      <div className="endofday-container">
        <div className="input-wrapper">
          <div className="text">Tarih</div>
          <div className="text date">24.12.2002</div>
        </div>
        <Button
          style={{
            width: "250px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <GetAppIcon style={{ marginRight: "15px", fontSize: "30px" }} />
          }
          size="large"
          variant="contained"
        >
          GÜN SONU AL
        </Button>
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
