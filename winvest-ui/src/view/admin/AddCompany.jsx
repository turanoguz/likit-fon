import React from "react";
import SaveIcon from "@mui/icons-material/Save";
import { TextField, Button } from "@mui/material";
export default function AddCompany() {
  return (
    <>
      <div className="company customer">
        <div className="input-wrapper">
          <div className="text">Şirket Adı</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch", marginRight: "50px" }}
            id="outlined-basic"
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Sicil Numarası</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch", marginRight: "50px" }}
            id="outlined-basic"
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Kurum Telefonu</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch", marginRight: "50px" }}
            id="outlined-basic"
            variant="outlined"
            //inputProps={{ tabIndex: "3" }}
          />
        </div>
        <div className="break"></div>
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
          <div className="text">Şirket Kuruluş Tarihi</div>
          <TextField
            className="text-field"
            //value={fundType}
            //onChange={(event) => setFundType(event.target.value)}
            size="small"
            sx={{ m: 1, width: "35ch" }}
            id="outlined-basic"
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
        ŞİRKET EKLE
      </Button>
    </>
  );
}
