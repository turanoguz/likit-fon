import axios from "axios";

const addCashEndpoint = "/api/customers/add/cash";
const withdrawCashEndpoint = "/api/customers/withdraw/money";

export async function addCash(request) {
  return await axios.put(addCashEndpoint, request);
}

export async function withdrawCash(request) {
  return await axios.put(withdrawCashEndpoint, request);
}
