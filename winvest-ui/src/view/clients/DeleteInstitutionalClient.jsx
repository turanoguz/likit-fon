import React, { useState } from "react";
import { TextField, Button } from "@mui/material";
import BackspaceIcon from "@mui/icons-material/Backspace";
import "./DeleteInstitutionalClient.css";
import { deleteInstitutionalClient } from "../../services/CustomerService";
import { toast } from "react-toastify";
import { toastOptions } from "../../Constants";

export default function DeleteInstitutionalClient() {
  const [taxNo, setTaxNo] = useState();
  const deleteInstitutional = () => {
    deleteInstitutionalClient(taxNo).then((res) => {
      if (!res.data.success) {
        toast.error("Kullanıcı silinemedi", toastOptions);
        return;
      }
      toast.success(res.data.message, toastOptions);
    });
  };
  return (
    <>
      <div className="del institutional customer">
        <div className="input-wrapper">
          <div className="text">VKN</div>
          <TextField
            className="text-field"
            value={taxNo}
            onChange={(event) => setTaxNo(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "1" }}
          />
        </div>
      </div>
      <Button
        onClick={deleteInstitutional}
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
        KURUMSAL SİL
      </Button>
    </>
  );
}
