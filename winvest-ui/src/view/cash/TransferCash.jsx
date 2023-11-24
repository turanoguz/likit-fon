import React, { useState } from "react";
import { OutlinedInput, InputAdornment, Button } from "@mui/material";
import { toast } from "react-toastify";
import CurrencyLiraIcon from "@mui/icons-material/CurrencyLira";
import "./TransferCash.css";
import UserLayout from "../../layout/user/UserLayout";
import Cash from "./Cash";
import { toastOptions } from "../../Constants";
import { withdrawCash } from "../../services/CashService";
export default function TransferCash() {
  const [balance, setBalance] = useState();
  const [amount, setAmount] = useState();
  const [customerId, setCustomerId] = useState();
  const callback = (customerId) => {
    setCustomerId(customerId);
  };
  const withdrawCashHandle = () => {
    withdrawCash({ id: customerId, amount })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Para Çekilemedi", toastOptions);
          return;
        }
        setBalance(res.data.data);
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => {
        toast.error(res.response.data.message, toastOptions);
      });
  };
  return (
    <UserLayout headerText={"HAVALE"}>
      <div className="transfercash-wrapper">
        <Cash callback={callback} cashBalance={balance} />
        <div className="buy customer">
          <div className="input-wrapper">
            <div className="text">Çekilecek Tutar</div>
            <OutlinedInput
              onChange={(event) => setAmount(event.target.value)}
              autoComplete="off"
              className="text-field"
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
          onClick={withdrawCashHandle}
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
          NAKİT ÇEK
        </Button>
      </div>
    </UserLayout>
  );
}
