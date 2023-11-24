import axios from "axios";

const getAddIndividualEndpoint = "/api/customers/individual/add";
const getDeleteIndividualEndpoint =
  "/api/customers/individual/update/setActive";
const getAddInstitutionalEndpoint = "/api/customers/institutional/add";
const getDeleteInstitutionalEndpoint =
  "/api/customers/institutional/update/setActive";
const getCustomerAndFundsByCustomerNoEndpoint =
  "/api/customers/get/funds/customerNo/";
const getCustomerByIdentityEndpoint = "/api/customers/individual/get/";
const getCustomerByNoEndpoint = "/api/customers/individual/get/customerNumber/";
const updateCustomerByIdEndpoint = "/api/customers/individual/update/";

const getCompanyByNoEndpoint =
  "/api/customers/institutional/get/customerNumber/";
const getCompanyByTaxNoEndpoint = "/api/customers/institutional/get/";
const updateCompanyByIdEndpoint = "/api/customers/institutional/update/";
const getAllIndividualClientsEndpoint = "/api/customers/individual/get/all";
const getAllInstitutionalClientsEndpoint =
  "/api/customers/institutional/get/all";

export async function postIndividualClient(request) {
  return await axios.post(getAddIndividualEndpoint, request);
}

export async function deleteIndividualClient(request) {
  return await axios.put(
    getDeleteIndividualEndpoint + "?identificationNumber=" + request
  );
}

export async function postInstitutionalClient(request) {
  return await axios.post(getAddInstitutionalEndpoint, request);
}

export async function deleteInstitutionalClient(request) {
  return await axios.put(getDeleteInstitutionalEndpoint + "?taxNo=" + request);
}

export async function getCustomerAndFundsByCustomerNo(request) {
  return await axios.get(getCustomerAndFundsByCustomerNoEndpoint + request);
}

export async function getCustomerByIdentity(request) {
  return await axios.get(getCustomerByIdentityEndpoint + request);
}

export async function getCustomerByNo(request) {
  return await axios.get(getCustomerByNoEndpoint + request);
}

export async function updateCustomerById(id, request) {
  return await axios.put(updateCustomerByIdEndpoint + id, request);
}

export async function getCompanyByNo(request) {
  return await axios.get(getCompanyByNoEndpoint + request);
}

export async function getCompanyByTaxNo(request) {
  return await axios.get(getCompanyByTaxNoEndpoint + request);
}

export async function updateCompanyById(id, request) {
  return await axios.put(updateCompanyByIdEndpoint + id, request);
}

export async function getAllIndividualClients() {
  return await axios.get(getAllIndividualClientsEndpoint);
}

export async function getAllInstitutionalClients() {
  return await axios.get(getAllInstitutionalClientsEndpoint);
}
