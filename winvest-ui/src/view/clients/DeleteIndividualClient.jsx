import React, { useState } from "react";
import { TextField, Button } from "@mui/material";
import BackspaceIcon from "@mui/icons-material/Backspace";
import "./DeleteIndividualClient.css";
import { deleteIndividualClient } from "../../services/CustomerService";
import { toastOptions } from "../../Constants";
import { toast } from "react-toastify";

export default function DeleteIndividualClient() {
  const [identificationNumber, setIdentificationNumber] = useState();
  const deleteIndividual = () => {
    deleteIndividualClient(identificationNumber).then((res) => {
      if (!res.data.success) {
        toast.error("Kullanıcı silinemedi", toastOptions);
        return;
      }
      toast.success(res.data.message, toastOptions);
    });
  };
  return (
    <>
      <div className="del individual customer">
        <div className="input-wrapper">
          <div className="text">TCKN</div>
          <TextField
            className="text-field"
            value={identificationNumber}
            onChange={(event) => setIdentificationNumber(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "1" }}
          />
        </div>
      </div>
      <Button
        onClick={deleteIndividual}
        tabIndex={2}
        style={{
          width: "300px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <BackspaceIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        BİREYSEL SİL
      </Button>
    </>
  );
}
