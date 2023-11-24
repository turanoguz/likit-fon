import React, { useState, useEffect } from "react";
import {
  TextField,
  Autocomplete,
  MenuItem,
  Button,
  OutlinedInput,
  InputAdornment,
} from "@mui/material";
import AttachMoneyIcon from "@mui/icons-material/AttachMoney";
import "./FundSell.css";
import { toast } from "react-toastify";
import { toastOptions } from "../../Constants";
import {
  getAllFundsCustomerByIdentity,
  getAllFundsCustomerByNo,
  sellFund,
} from "../../services/FundService";
export default function FundSell({
  customerId,
  identity,
  setBalance,
  customerNo,
  inc,
}) {
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
    if (inc === 0) return;
    setSelectedFund({
      fundPrice: 0,
    });
    setFundAmount("");
    setFundValue("");
    setFunds([]);
    if (customerNo !== undefined && customerNo !== "") {
      getAllFundsCustomerByNo(customerNo).then((res) => {
        setFunds(
          res.data.data[0].funds.map((value) => {
            return {
              fundName: value.fundname,
              fundPrice: value.unitPrice,
              fundQuantity: value.fundQuantity,
              fundId: value.fundId,
            };
          })
        );
      });
      return;
    }

    if (identity === undefined) return;
    getAllFundsCustomerByIdentity(identity).then((res) => {
      setFunds(
        res.data.data[0].funds.map((value) => {
          return {
            fundName: value.fundname,
            fundPrice: value.unitPrice,
            fundQuantity: value.fundQuantity,
            fundId: value.fundId,
          };
        })
      );
    });
  }, [inc]);

  useEffect(() => {
    onAmount(fundAmount);
  }, [selectedFund]);
  const sellFundHandler = () => {
    sellFund({
      customerId: customerId,
      fundId: selectedFund.fundId,
      fundQuantity: fundAmount,
      fundPrice: fundValue,
    })
      .then((res) => {
        if (!res.data.success) {
          toast.error("Fon Satılamadı", toastOptions);
          return;
        }
        const index = funds.findIndex(
          (fund) => fund.fundId === res.data.data.fundId
        );
        funds[index].fundQuantity = res.data.data.quantity;
        setFunds(funds);
        setBalance(res.data.data.money);
        toast.success(res.data.message, toastOptions);
      })
      .catch((res) => toast.error(res.response.data.message, toastOptions));
  };
  return (
    <>
      <div className="customer">
        <div className="input-wrapper">
          <div className="text">Fon Adı</div>
          <Autocomplete
            disabled={customerId === undefined ? true : false}
            className="text-field"
            size="small"
            sx={{ m: 1, width: "35ch", marginRight: "20px" }}
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
                <MenuItem key={value.fundId} value={value.fundId}>
                  {value.fundName}
                </MenuItem>
              );
            })}
          </Autocomplete>
        </div>
        <div className="input-wrapper">
          <div className="text">Fon Adet</div>
          <TextField
            className="text-field"
            onClick={() => onAmount(fundAmount)}
            value={fundAmount}
            onChange={(event) => {
              onAmount(event.target.value);
              setFundAmount(event.target.value);
            }}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            variant="outlined"
            inputProps={{ tabIndex: "4" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Fon Tutar</div>
          <OutlinedInput
            disabled={customerId === undefined ? true : false}
            startAdornment={<InputAdornment position="start">₺</InputAdornment>}
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
        <div className="break"></div>
        <div className="info input-wrapper">
          <div className="text">{selectedFund.fundQuantity} Adet Sahip</div>
        </div>
        <div className="info input-wrapper">
          <div className="text">1 Adet Fon: {selectedFund.fundPrice}TL</div>
        </div>
      </div>
      <Button
        onClick={sellFundHandler}
        style={{
          width: "200px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <AttachMoneyIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        FON SAT
      </Button>
    </>
  );
}
