import React from "react";
import infina from "../../assets/img/infinaLogo.png";
import FavoriteIcon from "@mui/icons-material/Favorite";
export default function Supports() {
  return (
    <div
      style={{
        display: "flex",
        gap: 50,
        width: "100%",
        height: "100vh",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <FavoriteIcon style={{ fontSize: 200, color: "red" }} />
      <img style={{ height: "135px" }} src={infina} />
      <FavoriteIcon style={{ fontSize: 200, color: "red" }} />
    </div>
  );
}
