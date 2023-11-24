import React, { useState } from "react";
import { TextField, Button } from "@mui/material";
import "./AddInstitutionalClient.css";
import SaveIcon from "@mui/icons-material/Save";
import { postInstitutionalClient } from "../../services/CustomerService";
import { toastOptions } from "../../Constants";
import { toast } from "react-toastify";

export default function AddInstitutionalClient() {
  const [email, setEmail] = useState();
  const [customerCreationDate, setCustomerCreationDate] = useState();
  const [customerTaxNo, setCustomerTaxNo] = useState();
  const [companyName, setCompanyName] = useState();
  const [customerPhone, setCustomerPhone] = useState();
  const [responseCustomerNo, setResponseCustomerNo] = useState();

  const saveInstitutional = () => {
    postInstitutionalClient({
      email,
      customerCreationDate,
      customerTaxNo,
      companyName,
      customerPhone,
      active: true,
    })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Kullanıcı oluşturulamadı", toastOptions);
          return;
        }
        setResponseCustomerNo(res.data.data);
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => toast.error(res.response.data.message, toastOptions));
  };

  return (
    <>
      <div className="institutional customer">
        <div className="input-wrapper">
          <div className="text">Kurum Adı</div>
          <TextField
            className="text-field"
            value={companyName}
            onChange={(event) => setCompanyName(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "1" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">VKN</div>
          <TextField
            className="text-field"
            value={customerTaxNo}
            onChange={(event) => setCustomerTaxNo(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "2" }}
          />
        </div>
        <div className="break"></div>
        <div className="info input-wrapper">
          <div className="text">Mail</div>
          <TextField
            className="text-field"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">Kurum İletişim No</div>
          <TextField
            className="text-field"
            value={customerPhone}
            onChange={(event) => setCustomerPhone(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "4" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">Kayıt Tarihi</div>
          <TextField
            className="text-field"
            value={customerCreationDate}
            onChange={(event) => setCustomerCreationDate(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            label="GG/AA/YYYY"
            inputProps={{ tabIndex: "5" }}
          />
        </div>
      </div>
      <div className="info input-wrapper">
        <div className="text">Müşteri NO</div>
        <div className="text">{responseCustomerNo}</div>
      </div>
      <Button
        onClick={saveInstitutional}
        tabIndex={6}
        style={{
          width: "300px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        KURUMSAL KAYDET
      </Button>
    </>
  );
}
