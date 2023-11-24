import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { Button } from "@mui/material";
import "./Clients.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import IndividualClients from "./IndividualClients";
import InstitutionalClients from "./InstitutionalClients";
export default function Clients() {
  const [individualOrInstitutional, setIndividualOrInstitutional] =
    useState(true);

  const individual = () => {
    if (individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };
  const institutional = () => {
    if (!individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };

  return (
    <UserLayout headerText={"MÜŞTERİLER"}>
      <div className="client-wrapper">
        <div className="clients client-buttons">
          <div className="ioi-buttons">
            <Button
              onClick={individual}
              className={`ioi-button ${
                individualOrInstitutional ? "active" : ""
              }`}
              variant="contained"
            >
              BİREYSEL
            </Button>
            <Button
              onClick={institutional}
              className={`ioi-button ${
                !individualOrInstitutional ? "active" : ""
              }`}
              variant="contained"
            >
              KURUMSAL
            </Button>
          </div>
        </div>
        {individualOrInstitutional ? (
          <IndividualClients />
        ) : (
          <InstitutionalClients />
        )}
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
