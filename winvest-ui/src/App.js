import "./App.css";
import { Route, Routes } from "react-router-dom";
import axios from "axios";
import Login from "./view/login/Login";
import Dashboard from "./view/dashboard/Dashboard";
import FundCreate from "./view/fund/FundCreate";
import FundBuySell from "./view/fund/FundBuySell";
import FundEndOfDay from "./view/fund/FundEndOfDay";
import ClientEdit from "./view/clients/ClientEdit";
import Clients from "./view/clients/Clients";
import AddCash from "./view/cash/AddCash";
import TransferCash from "./view/cash/TransferCash";
import FundDetails from "./view/fund/FundDetails";
import ClientUpdate from "./view/clients/ClientUpdate";
import Admin from "./view/admin/Admin";
import Logout from "./view/login/Logout";
import Supports from "./view/clients/Supports";

const App = () => {
  axios.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${localStorage.getItem("token")}`;
  return (
    <div className="App">
      <Routes>
        {!localStorage.getItem("token") ? (
          <Route path="*" element={<Login />}></Route>
        ) : (
          <>
            <Route path="/" element={<Dashboard />}></Route>
            <Route path="/fund">
              <Route path="create" element={<FundCreate />}></Route>
              <Route path="trade" element={<FundBuySell />}></Route>
              <Route path="details" element={<FundDetails />}></Route>
              <Route path="endofday" element={<FundEndOfDay />}></Route>
            </Route>
            <Route path="/clients">
              <Route path="" element={<Clients />}></Route>
              <Route path="edit" element={<ClientEdit />}></Route>
              <Route path="update" element={<ClientUpdate />}></Route>
            </Route>
            <Route path="/cash">
              <Route path="add" element={<AddCash />}></Route>
              <Route path="transfer" element={<TransferCash />}></Route>
            </Route>
            <Route path="/admin" element={<Admin />}></Route>
            <Route path="/support" element={<Supports />}></Route>
            <Route path="/logout" element={<Logout />}></Route>
          </>
        )}
      </Routes>
    </div>
  );
};

export default App;
