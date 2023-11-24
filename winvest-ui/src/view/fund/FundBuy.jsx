import React, { useEffect, useState } from "react";
import {
  TextField,
  Autocomplete,
  MenuItem,
  Button,
  OutlinedInput,
  InputAdornment,
} from "@mui/material";
import ShoppingBasketIcon from "@mui/icons-material/ShoppingBasket";
import { toast } from "react-toastify";

import "./FundBuy.css";
import { buyFund, getAllFunds } from "../../services/FundService";
import { toastOptions } from "../../Constants";
export default function FundBuy({ customerId, setBalance }) {
  const [selectedFund, setSelectedFund] = useState({
    fundPrice: 0,
  });
  const [fundAmount, setFundAmount] = useState("");
  const [fundValue, setFundValue] = useState("");

  const [funds, setFunds] = useState([]);

  const onAmount = (amount) => {
    if (selectedFund.fundPrice === 0) return;
    if (amount === "") {
      setFundValue(0);
      return;
    }
    setFundValue(
      (
        Math.ceil(parseInt(amount) * selectedFund.fundPrice * 100) / 100
      ).toFixed(2)
    );
  };
  const onValue = (value) => {
    if (selectedFund.fundPrice === 0) return;
    if (value === "") {
      setFundAmount(0);
      return;
    }

    setFundAmount(parseInt(parseFloat(value) / selectedFund.fundPrice));
  };

  useEffect(() => {
    getAllFunds().then((res) => {
      setFunds(res.data.data.map((value) => value));
    });
  }, []);

  useEffect(() => {
    onAmount(fundAmount);
  }, [selectedFund]);

  const buyFundHandler = () => {
    buyFund({
      customerId: customerId,
      fundId: selectedFund.id,
      fundQuantity: fundAmount,
      fundPrice: fundValue,
    })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Fon alınamadı", toastOptions);
          return;
        }
        setBalance(res.data.data);
        toast.success(res.data.message, toastOptions);
      })
      .catch((err) => {
        toast.error(err.response.data.message, toastOptions);
      });
  };

  return (
    <>
      <div className="buy customer">
        <div className="input-wrapper">
          <div className="text">Fon Adı</div>
          <Autocomplete
            disabled={customerId === undefined ? true : false}
            className="text-field"
            size="small"
            sx={{ m: 1, width: "50ch", marginRight: "20px" }}
            tabIndex={3}
            options={funds}
            getOptionLabel={(option) => option.fundName}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            onChange={(event, newValue) => {
              if (newValue === null) return;
              setSelectedFund(newValue);
            }}
            disablePortal
            renderInput={(params) => <TextField {...params} />}
          >
            {funds.map((value, index) => {
              return (
                <MenuItem key={value.id} value={value.id}>
                  {value.fundName}
                </MenuItem>
              );
            })}
          </Autocomplete>
          <div style={{ width: "150px" }} className="text">
            {selectedFund.fundPrice}TL
          </div>
        </div>
        <div className="wrapper-in-wrapper">
          <div className="input-wrapper">
            <div className="text">Fon Adet</div>
            <TextField
              disabled={customerId === undefined ? true : false}
              onClick={() => onAmount(fundAmount)}
              className="text-field"
              value={fundAmount}
              onChange={(event) => {
                onAmount(event.target.value);
                setFundAmount(event.target.value);
              }}
              size="small"
              sx={{ m: 1, width: "35ch", marginRight: "50px" }}
              variant="outlined"
              inputProps={{ tabIndex: "4" }}
            />
          </div>
          <div className="input-wrapper">
            <div className="text">Fon Tutar</div>
            <OutlinedInput
              disabled={customerId === undefined ? true : false}
              startAdornment={
                <InputAdornment position="start">₺</InputAdornment>
              }
              onClick={() => onValue(fundValue)}
              className="text-field"
              value={fundValue}
              onChange={(event) => {
                onValue(event.target.value);
                setFundValue(event.target.value);
              }}
              size="small"
              sx={{ m: 1, width: "35ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "5" }}
            />
          </div>
        </div>
      </div>
      <Button
        tabIndex={6}
        onClick={buyFundHandler}
        style={{
          width: "200px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <ShoppingBasketIcon
            style={{ marginRight: "15px", fontSize: "30px" }}
          />
        }
        size="large"
        variant="contained"
      >
        FON AL
      </Button>
    </>
  );
}
