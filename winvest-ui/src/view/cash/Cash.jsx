import React, { useEffect, useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { TextField, Button } from "@mui/material";
import "./Cash.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import PersonIcon from "@mui/icons-material/Person";
import { getCustomerAndFundsByCustomerNo } from "../../services/CustomerService";
import { getCustomer } from "../../services/FundService";
import { toastOptions } from "../../Constants";
export default function Cash({ callback, cashBalance }) {
  const [username, setUsername] = useState();
  const [balance, setBalance] = useState(0);
  const [customerNo, setCustomerNo] = useState();
  const [identity, setIdentity] = useState();

  useEffect(() => {
    if (cashBalance === undefined) return;
    setBalance(cashBalance);
  }, [cashBalance]);

  //context sistemi kurulabilirdi ama kurmadım çünkü vakit kalmadı :)
  const fetchUser = () => {
    if (customerNo) {
      getCustomerAndFundsByCustomerNo(customerNo)
        .then((res) => {
          if (!res.data.success) {
            toast.error("Müşteri Getirilemedi", toastOptions);
            return;
          }
          setUsername(res.data.data[0].customerName);
          setBalance(res.data.data[0].totalMoney);
          callback(res.data.data[0].customerId);
          toast.success("Kullanıcı Başarıyla Getirildi", toastOptions);
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
        callback(res.data.data[0].id);
        toast.success("Kullanıcı Başarıyla Getirildi", toastOptions);
      })
      .catch((res) => toast.error(res.response.data.message, toastOptions));
  };

  return (
    <>
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
        onClick={fetchUser}
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
      <ToastContainer />
    </>
  );
}
