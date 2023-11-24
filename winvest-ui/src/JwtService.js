import jwtDecode from "jwt-decode";
export function extractClaimsFromJWT() {
  var token = localStorage.getItem("token");
  return jwtDecode(token);
}
