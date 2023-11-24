import axios from "axios";

const getAllFundsEndpoint = "/api/funds/get/all";
const postFundEndpoint = "/api/funds/add";
const getCustomerEndpoint = "/api/customers/get/accounts/";
const getBuyFundEndpoint = "/api/CustomerFunds/buyFund";
const getSellFundEndpoint = "/api/CustomerFunds/sell/fund";
const getAllFundsCustomerByIdentityEndpoint = "/api/customers/get/funds/";
const getAllFundsCustomerByNoEndpoint = "/api/customers/get/funds/customerNo/";
const getMostValuedFundEndpoint = "/api/funds/get/most-valued/";
const getLogsFundEndpoint = "/api/funds/get/week/";

export async function getAllFunds() {
  return await axios.get(getAllFundsEndpoint);
}

export async function postFund(request) {
  return await axios.post(postFundEndpoint, request);
}

export async function getCustomer(request) {
  return await axios.get(getCustomerEndpoint + request);
}

export async function buyFund(request) {
  return await axios.put(getBuyFundEndpoint, request);
}

export async function sellFund(request) {
  return await axios.put(getSellFundEndpoint, request);
}

export async function getAllFundsCustomerByIdentity(request) {
  return await axios.get(getAllFundsCustomerByIdentityEndpoint + request);
}

export async function getAllFundsCustomerByNo(request) {
  return await axios.get(getAllFundsCustomerByNoEndpoint + request);
}

export async function getMostValuedFund() {
  return await axios.get(getMostValuedFundEndpoint);
}

export async function getLogsFund(request) {
  return await axios.get(getLogsFundEndpoint + request);
}
