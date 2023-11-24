import React, { useState } from "react";
import SaveIcon from "@mui/icons-material/Save";
import {
  TextField,
  Button,
  Autocomplete,
  OutlinedInput,
  InputAdornment,
  IconButton,
} from "@mui/material";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
export default function AddWorker() {
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
          <div className="text">Şube</div>
          <Autocomplete
            className="text-field"
            size="small"
            value={branch}
            options={branches}
            disabled
            isOptionEqualToValue={(option, value) =>
              option.value === value.value
            }
            getOptionLabel={(option) => option.title}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            //onChange={(event, newValue) => onChange(newValue)}
            disablePortal
            sx={{ m: 1, width: 300 }}
            renderInput={(params) => <TextField {...params} />}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Kullanıcı Adı</div>
          <TextField
            className="text-field"
            autoComplete="off"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Parola</div>
          <OutlinedInput
            shrink="true"
            className="text-field"
            size="small"
            sx={{ m: 1, width: 300, marginRight: "50px" }}
            //value={password}
            //onChange={(event) => setPassword(event.target.value)}
            type={showPassword ? "text" : "password"}
            autoComplete="off"
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="şifre görünürlüğünü aç"
                  onClick={() => setShowPassword(!showPassword)}
                  edge="end"
                >
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
            notched
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Sicil Numarası</div>
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
          <div className="text">Adı</div>
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
          <div className="text">Soyadı</div>
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
          <div className="text">Telefon</div>
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
          <div className="text">İşe Girme Tarihi</div>
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
