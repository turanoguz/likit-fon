import axios from "axios";

function getUrl() {
  return "http://localhost:8080/api/v1/auth/authenticate";
}

export async function authenticate(request) {
  return await axios.post(getUrl(), request);
}
