import React, { useState } from "react";
import { OutlinedInput, InputAdornment, Button } from "@mui/material";
import { toast } from "react-toastify";
import CurrencyLiraIcon from "@mui/icons-material/CurrencyLira";
import "./AddCash.css";
import UserLayout from "../../layout/user/UserLayout";
import Cash from "./Cash";
import { addCash } from "../../services/CashService";
import { toastOptions } from "../../Constants";
export default function AddCash() {
  const [balance, setBalance] = useState();
  const [amount, setAmount] = useState();
  const [customerId, setCustomerId] = useState();
  const callback = (customerId) => {
    setCustomerId(customerId);
  };
  const addCashHandle = () => {
    addCash({ id: customerId, amount })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Para Eklenemedi", toastOptions);
          return;
        }
        setBalance(res.data.data);
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => toast.error(res.response.data.message, toastOptions));
  };

  return (
    <UserLayout headerText={"NAKİT EKLE"}>
      <div className="addcash-wrapper">
        <Cash callback={callback} cashBalance={balance} />
        <div className="buy customer">
          <div className="input-wrapper">
            <div className="text">Yatırılacak Tutar</div>
            <OutlinedInput
              autoComplete="off"
              className="text-field"
              onChange={(event) => setAmount(event.target.value)}
              startAdornment={
                <InputAdornment position="start">₺</InputAdornment>
              }
              type="number"
              size="small"
              sx={{ m: 1, width: "35ch", marginRight: "50px" }}
              variant="outlined"
            />
          </div>
        </div>
        <Button
          onClick={addCashHandle}
          style={{
            width: "250px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <CurrencyLiraIcon
              style={{ marginRight: "15px", fontSize: "30px" }}
            />
          }
          size="large"
          variant="contained"
        >
          NAKİT EKLE
        </Button>
      </div>
    </UserLayout>
  );
}
