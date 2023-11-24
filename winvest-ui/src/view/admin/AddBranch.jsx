import React, { useState } from "react";
import SaveIcon from "@mui/icons-material/Save";
import { TextField, Button, Autocomplete } from "@mui/material";
export default function AddBranch() {
  const [showPassword, setShowPassword] = useState(false);
  const [company, setCompany] = useState({ title: "Şirketi", id: 1 });
  const [branch, setBranch] = useState({ title: "şubesi", id: 1 });
  const [companies, setCompanies] = useState([]);
  const [branches, setBranches] = useState([{ title: "Genel Şirket", id: -1 }]);
  return (
    <>
      <div className="company customer">
        <div className="input-wrapper">
          <div className="text">Şirket</div>
          <Autocomplete
            className="text-field"
            size="small"
            value={company}
            options={companies}
            disabled
            getOptionLabel={(option) => option.title}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            //onChange={(event, newValue) => onChange(newValue)}
            disablePortal
            sx={{ m: 1, width: 300 }}
            renderInput={(params) => <TextField {...params} />}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Şube ID</div>
          <TextField
            className="text-field"
            type="number"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Mail Adresi</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Şube Telefonu</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="break"></div>
        <div className="input-wrapper">
          <div className="text">Şehir</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">İlçe</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Adres</div>
          <TextField
            className="text-field"
            multiline
            maxRows={5}
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            id="outlined-basic"
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Servis Tarihi</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300 }}
            variant="outlined"
            label="GG/AA/YYYY"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
      </div>
      <Button
        style={{
          width: "250px",
          height: "60px",
          fontSize: "20px",
        }}
        startIcon={
          <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
        }
        size="large"
        variant="contained"
      >
        ÇALIŞAN EKLE
      </Button>
    </>
  );
}
