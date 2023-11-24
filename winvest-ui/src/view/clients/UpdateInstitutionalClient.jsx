import React, { useEffect, useState } from "react";
import { TextField, Button } from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import { updateCompanyById } from "../../services/CustomerService";
import { toast } from "react-toastify";
import { toastOptions } from "../../Constants";

export default function UpdateInstitutionalClient({ client }) {
  //client.id
  useEffect(() => {
    if (client === undefined || Object.keys(client).length === 0) return;
    setEmail(client.email);
    setCustomerTaxNo(client.customerTaxNo);
    setCompanyName(client.companyName);
    setCustomerPhone(client.customerPhone);
  }, [client]);
  const [email, setEmail] = useState();
  const [customerTaxNo, setCustomerTaxNo] = useState();
  const [companyName, setCompanyName] = useState();
  const [customerPhone, setCustomerPhone] = useState();

  const update = () => {
    updateCompanyById(client.id, {
      email: email,
      customerTaxNo: customerTaxNo,
      companyName: companyName,
      customerPhone: customerPhone,
      active: true,
    })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Müşteri güncellenemedi!!", toastOptions);
          return;
        }
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => toast.error("Müşteri güncellenemedi!!", toastOptions));
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
            inputProps={{ tabIndex: "3" }}
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
            inputProps={{ tabIndex: "4" }}
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
            inputProps={{ tabIndex: "5" }}
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
            inputProps={{ tabIndex: "6" }}
          />
        </div>
      </div>
      <Button
        onClick={update}
        style={{
          width: "350px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        KURUMSAL GÜNCELLE
      </Button>
    </>
  );
}
