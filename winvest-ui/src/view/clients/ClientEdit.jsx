import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { TextField, Select, MenuItem, Button } from "@mui/material";
import "./ClientEdit.css";
import GetAppIcon from "@mui/icons-material/GetApp";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import AddIndividualClient from "./AddIndividualClient";
import DeleteIndividualClient from "./DeleteIndividualClient";
import AddInstitutionalClient from "./AddInstitutionalClient";
import DeleteInstitutionalClient from "./DeleteInstitutionalClient";
export default function ClientEdit() {
  const [addOrRemove, setAddOrRemove] = useState(true);
  const [individualOrInstitutional, setIndividualOrInstitutional] =
    useState(true);
  const add = () => {
    if (addOrRemove) return;
    setAddOrRemove(!addOrRemove);
  };
  const remove = () => {
    if (!addOrRemove) return;
    setAddOrRemove(!addOrRemove);
  };

  const individual = () => {
    if (individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };
  const institutional = () => {
    if (!individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };

  return (
    <UserLayout headerText={"Müşteri Ekle - Çıkar"}>
      <div className="client-wrapper">
        <div className="client-buttons">
          <div className="addorremove-buttons">
            <Button
              onClick={add}
              className={`addorremove-button ${addOrRemove ? "active" : ""}`}
              variant="contained"
            >
              EKLE
            </Button>
            <Button
              onClick={remove}
              className={`addorremove-button ${!addOrRemove ? "active" : ""}`}
              variant="contained"
            >
              ÇIKAR
            </Button>
          </div>
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
          addOrRemove ? (
            <AddIndividualClient />
          ) : (
            <DeleteIndividualClient />
          )
        ) : addOrRemove ? (
          <AddInstitutionalClient />
        ) : (
          <DeleteInstitutionalClient />
        )}
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
