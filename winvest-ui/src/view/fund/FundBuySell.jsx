import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { TextField, Button } from "@mui/material";
import "./FundBuySell.css";
import FundSell from "./FundSell";
import FundBuy from "./FundBuy";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PersonIcon from "@mui/icons-material/Person";
import { getCustomer } from "../../services/FundService";
import { toastOptions } from "../../Constants";
import { getCustomerAndFundsByCustomerNo } from "../../services/CustomerService";
export default function FundBuySell() {
  const [activate, setActivate] = useState(true);
  const [username, setUsername] = useState();
  const [balance, setBalance] = useState(0);
  const [customerId, setCustomerId] = useState();
  const [customerNo, setCustomerNo] = useState();
  const [identity, setIdentity] = useState();
  const [inc, setInc] = useState(0);

  const activateBuyHandler = () => {
    if (activate) return;
    setActivate(!activate);
  };
  const activateSellHandler = () => {
    if (!activate) return;
    setActivate(!activate);
  };

  const fetchCustomer = () => {
    if (customerNo) {
      getCustomerAndFundsByCustomerNo(customerNo)
        .then((res) => {
          if (!res.data.success) {
            toast.error("Müşteri Getirilemedi", toastOptions);
            return;
          }
          setUsername(res.data.data[0].customerName);
          setBalance(res.data.data[0].totalMoney);
          setCustomerId(res.data.data[0].customerId);
          toast.success("Kullanıcı Başarıyla Getirildi", toastOptions);
          setInc((prev) => prev + 1);
        })
        .catch((res) => toast.error(res.response.data.message, toastOptions));
      return;
    }
    getCustomer(identity)
      .then((res) => {
        if (!res.data.success) {
          toast.error("Müşteri Getirilemedi", toastOptions);
          return;
        }
        setUsername(res.data.data[0].name);
        setBalance(res.data.data[0].accounts[0].totalMoney);
        setCustomerId(res.data.data[0].id);
        toast.success("Kullanıcı Başarıyla Getirildi", toastOptions);
        setInc((prev) => prev + 1);
      })
      .catch((res) => toast.error(res.response.data.message, toastOptions));
  };

  return (
    <UserLayout headerText={"Fon Alım - Satım"}>
      <div className="buysell-container">
        <div className="buysell-buttons">
          <Button
            onClick={activateBuyHandler}
            className={`buysell-button ${activate ? "active" : ""}`}
            variant="contained"
          >
            Alım
          </Button>
          <Button
            onClick={activateSellHandler}
            className={`buysell-button ${!activate ? "active" : ""}`}
            variant="contained"
          >
            Satım
          </Button>
        </div>
        <div className="customer">
          <div className="input-wrapper">
            <div className="text">Müşteri Numarası</div>
            <TextField
              className="text-field"
              value={customerNo}
              onChange={(event) => setCustomerNo(event.target.value)}
              size="small"
              sx={{ m: 1, width: "35ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "1" }}
            />
          </div>
          <div className="input-wrapper">
            <div className="text">TCKimlik | VKN</div>
            <TextField
              className="text-field"
              value={identity}
              onChange={(event) => setIdentity(event.target.value)}
              size="small"
              sx={{ m: 1, width: "35ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "2" }}
            />
          </div>
          <div className="break"></div>
          <div className="input-wrapper">
            <div className="text">{username}</div>
          </div>
          <div className="input-wrapper">
            <div className="text">
              Bakiye {balance === 0 ? 0 : balance.toFixed(2)}TL
            </div>
          </div>
        </div>
        <Button
          onClick={fetchCustomer}
          style={{
            width: "300px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <PersonIcon style={{ marginRight: "15px", fontSize: "30px" }} />
          }
          size="large"
          variant="contained"
        >
          MÜŞTERİ GETİR
        </Button>
        {activate ? (
          <FundBuy customerId={customerId} setBalance={setBalance}></FundBuy>
        ) : (
          <FundSell
            identity={identity}
            customerId={customerId}
            customerNo={customerNo}
            setBalance={setBalance}
            inc={inc}
          ></FundSell>
        )}
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
