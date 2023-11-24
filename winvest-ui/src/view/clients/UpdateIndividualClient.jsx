import React, { useEffect, useState } from "react";
import { TextField, Button } from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import { updateCustomerById } from "../../services/CustomerService";
import { toast } from "react-toastify";
import { toastOptions } from "../../Constants";

export default function UpdateIndividualClient({ client }) {
  useEffect(() => {
    if (
      client === undefined ||
      Object.keys(client).length === 0 ||
      !Object.keys(client).includes("customerDateOfBrith") ||
      !Object.keys(client).includes("customerCreationDate")
    )
      return;
    let brth = client.customerDateOfBrith.split("-");
    setCustomerFirstName(client.customerFirstName);
    setCustomerLastname(client.customerLastname);
    setIdentificationNumber(client.identificationNumber);
    setEmail(client.email);
    setCustomerDateOfBrith(`${brth[2]}/${brth[1]}/${brth[0]}`);
    setCustomerPhoneNumber(client.customerPhoneNumber);
    setCustomerBusinessNumber(client.customerBusinessNumber);
    setAddress(client.address);
  }, [client]);
  const [customerFirstName, setCustomerFirstName] = useState();
  const [customerLastname, setCustomerLastname] = useState();
  const [identificationNumber, setIdentificationNumber] = useState();
  const [email, setEmail] = useState();
  const [customerDateOfBrith, setCustomerDateOfBrith] = useState();
  const [customerPhoneNumber, setCustomerPhoneNumber] = useState();
  const [customerBusinessNumber, setCustomerBusinessNumber] = useState();
  const [address, setAddress] = useState();

  const update = () => {
    let crtDate = client.customerCreationDate.split("-");
    updateCustomerById(client.id, {
      customerFirstName,
      customerLastname,
      identificationNumber,
      email,
      customerDateOfBrith,
      customerPhoneNumber,
      customerBusinessNumber,
      address,
      customerCreationDate: `${crtDate[2]}/${crtDate[1]}/${crtDate[0]}`,
      active: true,
    })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Müşteri güncellenemedi!!", toastOptions);
          return;
        }
        toast.success(res.data.message, toastOptions);
      })
      .catch(() => toast.error("Müşteri güncellenemedi!!", toastOptions));
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
            inputProps={{ tabIndex: "3" }}
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
            inputProps={{ tabIndex: "4" }}
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
            inputProps={{ tabIndex: "5" }}
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
            inputProps={{ tabIndex: "6" }}
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
            inputProps={{ tabIndex: "7" }}
            InputLabelProps={{ shrink: true }}
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
            inputProps={{ tabIndex: "8" }}
          />
        </div>
        <div className="info input-wrapper">
          <div className="text">İş Numarası</div>
          <TextField
            className="text-field"
            value={customerBusinessNumber}
            onChange={(event) => setCustomerBusinessNumber(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "9" }}
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
            inputProps={{ tabIndex: "10" }}
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
        BİREYSEL GÜNCELLE
      </Button>
    </>
  );
}
