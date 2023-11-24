import React from "react";
import { useState } from "react";
import "./LoginStyle.css";
import logo from "../../assets/img/logo.png";
import {
  Alert,
  AlertTitle,
  IconButton,
  OutlinedInput,
  InputLabel,
  InputAdornment,
  FormControl,
  TextField,
} from "@mui/material";
import LoadingButton from "@mui/lab/LoadingButton";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import { authenticate } from "../../AuthenticateService";

function Login() {
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const [errorMessage, setErrorMessage] = useState("");

  const handleClickShowPassword = () => setShowPassword((show) => !show);
  const handleClick = () => {
    setLoading(true);
    authenticate({ username, password })
      .then((res) => {
        localStorage.setItem("token", res.data.access_token);
        window.location.reload();
      })
      .catch((err) => {
        setErrorMessage(err.response.data);
        setLoading(false);
      });
  };

  const handleMouseDownPassword = (event) => {
    event.preventDefault();
  };

  return (
    <div className="login">
      <Alert
        style={{
          display: errorMessage === "" ? "none" : "flex",
          width: "300px",
          marginBottom: "16px",
        }}
        severity="error"
      >
        <AlertTitle>Hata</AlertTitle>
        <strong>{errorMessage}</strong>
      </Alert>
      <div className="box">
        <div className="logo-container">
          <img alt="winvest logo" className="logo" src={logo}></img>
          <span className="winwest-text">WinVest</span>
        </div>
        <TextField
          InputLabelProps={{ shrink: true }}
          value={username}
          onChange={(event) => setUsername(event.target.value)}
          sx={{ m: 1, width: "35ch" }}
          label="Kullanıcı Adınız"
          variant="outlined"
        />

        <FormControl sx={{ m: 1, width: "35ch" }} variant="outlined">
          <InputLabel shrink={true} htmlFor="outlined-adornment-password">
            Şifreniz
          </InputLabel>
          <OutlinedInput
            shrink="true"
            value={password}
            onChange={(event) => setPassword(event.target.value)}
            id="outlined-adornment-password"
            type={showPassword ? "text" : "password"}
            endAdornment={
              <InputAdornment position="end">
                <IconButton
                  aria-label="şifre görünürlüğünü aç"
                  onClick={handleClickShowPassword}
                  onMouseDown={handleMouseDownPassword}
                  edge="end"
                >
                  {showPassword ? <VisibilityOff /> : <Visibility />}
                </IconButton>
              </InputAdornment>
            }
            notched
            label="Şifreniz"
          />
        </FormControl>
        <LoadingButton
          size="MEDIUM"
          onClick={handleClick}
          loading={loading}
          variant="contained"
          style={{
            marginTop: "10px",
            minWidth: "100px",
            textTransform: "none",
          }}
        >
          <span>Giriş Yap</span>
        </LoadingButton>
      </div>
    </div>
  );
}

export default Login;
