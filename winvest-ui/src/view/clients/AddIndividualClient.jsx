import React, { useState } from "react";
import { TextField, Button } from "@mui/material";
import "./AddIndividualClient.css";
import SaveIcon from "@mui/icons-material/Save";
import { postIndividualClient } from "../../services/CustomerService";
import { toast } from "react-toastify";
import { toastOptions } from "../../Constants";

export default function AddIndividualClient() {
  const [customerFirstName, setCustomerFirstName] = useState();
  const [customerLastname, setCustomerLastname] = useState();
  const [identificationNumber, setIdentificationNumber] = useState();
  const [email, setEmail] = useState();
  const [customerDateOfBrith, setCustomerDateOfBrith] = useState();
  const [customerPhoneNumber, setCustomerPhoneNumber] = useState();
  const [customerBusinessNumber, setCustomerBusinesNumber] = useState();
  const [address, setAddress] = useState();
  const [customerCreationDate, setCustomerCreationDate] = useState();
  const [responseCustomerNo, setResponseCustomerNo] = useState();

  const individualSave = () => {
    postIndividualClient({
      customerFirstName,
      customerLastname,
      identificationNumber,
      email,
      customerDateOfBrith,
      customerPhoneNumber,
      customerBusinessNumber,
      address,
      customerCreationDate,
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
      <div className="individual customer">
        <div className="input-wrapper">
          <div className="text">Ad</div>
          <TextField
            className="text-field"
            value={customerFirstName}
            onChange={(event) => setCustomerFirstName(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "1" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Soyad</div>
          <TextField
            className="text-field"
            value={customerLastname}
            onChange={(event) => setCustomerLastname(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "2" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">TCKN</div>
          <TextField
            className="text-field"
            value={identificationNumber}
            onChange={(event) => setIdentificationNumber(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Mail</div>
          <TextField
            className="text-field"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "4" }}
          />
        </div>
        <div className="break"></div>
        <div className="info input-wrapper">
          <div className="text">Doğum Tarihi</div>
          <TextField
            className="text-field"
            value={customerDateOfBrith}
            onChange={(event) => setCustomerDateOfBrith(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            label="GG/AA/YYYY"
            inputProps={{ tabIndex: "5" }}
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
            inputProps={{ tabIndex: "6" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">Cep Numarası</div>
          <TextField
            className="text-field"
            value={customerPhoneNumber}
            onChange={(event) => setCustomerPhoneNumber(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "7" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">İş Numarası</div>
          <TextField
            className="text-field"
            value={customerBusinessNumber}
            onChange={(event) => setCustomerBusinesNumber(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "8" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">Adres</div>
          <TextField
            className="text-field"
            multiline
            maxRows={5}
            value={address}
            onChange={(event) => setAddress(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "9" }}
          />
        </div>
      </div>
      <div className="info input-wrapper">
        <div className="text">Müşteri NO</div>
        <div className="text">{responseCustomerNo}</div>
      </div>
      <Button
        onClick={individualSave}
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
        tabIndex={10}
      >
        BİREYSEL KAYDET
      </Button>
    </>
  );
}
