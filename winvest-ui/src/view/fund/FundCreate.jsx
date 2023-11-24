import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import {
  TextField,
  Button,
  InputAdornment,
  OutlinedInput,
} from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import "./FundCreate.css";
import { postFund } from "../../services/FundService";
import { ToastContainer, toast } from "react-toastify";
import { toastOptions } from "../../Constants";
export default function FundCreate() {
  const [fundName, setFundName] = useState("");
  const [fundCode, setFundCode] = useState("");
  const [fundType, setFundType] = useState("");
  const [fundFounder, setFundFounder] = useState("");
  const [fundPrice, setFundPrice] = useState();
  //const [currencyType, setCurrencyType] = useState("TL");
  //const [riskValue, setRiskValue] = useState(4);
  const fundSave = () => {
    postFund({ fundName, fundCode, fundType, fundFounder, fundPrice })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Hatalı Bir Fon Kaydı", toastOptions);
          return;
        }
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => {
        toast.error(res.response.data.message, toastOptions);
      });
    //clearInputs();
  };
  const clearInputs = () => {
    setFundName("");
    setFundCode("");
    setFundType("");
    setFundFounder("");
    setFundPrice("");
    //setCurrencyType("TL");
    //setRiskValue(4);
  };

  return (
    <UserLayout headerText={"Fon Tanımlama"}>
      <div className="fund-create-container">
        <div className="fund-container">
          <div className="input-wrapper">
            <div className="text">Fon Adı</div>
            <TextField
              className="text-field"
              value={fundName}
              onChange={(event) => setFundName(event.target.value)}
              size="small"
              sx={{ m: 1, width: 300 }}
              variant="outlined"
              inputProps={{ tabIndex: "1" }}
            />
          </div>
          <div className="input-wrapper">
            <div className="text">Fon Kodu</div>
            <TextField
              className="text-field"
              inputProps={{
                min: 0,
                style: { textAlign: "center" },
                tabIndex: "2",
              }}
              value={fundCode}
              onChange={(event) => setFundCode(event.target.value)}
              size="small"
              sx={{ m: 1, width: "7ch" }}
              variant="outlined"
            />
          </div>
          <div className="input-wrapper">
            <div className="text">Fon Tipi</div>
            <TextField
              className="text-field"
              value={fundType}
              onChange={(event) => setFundType(event.target.value)}
              size="small"
              sx={{ m: 1, width: "25ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "3" }}
            />
          </div>
          <div className="input-wrapper">
            <div className="text">Fon Kurucusu</div>
            <TextField
              className="text-field"
              value={fundFounder}
              onChange={(event) => setFundFounder(event.target.value)}
              size="small"
              sx={{ m: 1, width: 300 }}
              variant="outlined"
              inputProps={{ tabIndex: "4" }}
            />
          </div>
          <div className="input-wrapper">
            <div className="text">Fon Fiyatı</div>
            <OutlinedInput
              className="text-field"
              type="number"
              value={fundPrice}
              startAdornment={
                <InputAdornment position="start">₺</InputAdornment>
              }
              onChange={(event) => setFundPrice(event.target.value)}
              size="small"
              sx={{ m: 1, width: 300 }}
              variant="outlined"
              inputProps={{ tabIndex: "5" }}
            />
          </div>
        </div>
      </div>
      <Button
        tabIndex={9}
        onClick={fundSave}
        style={{
          marginLeft: "43%",
          marginTop: "25px",
          width: "200px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        KAYDET
      </Button>
      <ToastContainer />
    </UserLayout>
  );
}
