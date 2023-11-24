import React, { useState } from "react";
import UserLayout from "../../layout/user/UserLayout";
import { TextField, Button } from "@mui/material";
import "./ClientEdit.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "./ClientUpdate.css";
import PersonIcon from "@mui/icons-material/Person";
import UpdateIndividualClient from "./UpdateIndividualClient";
import UpdateInstitutionalClient from "./UpdateInstitutionalClient";
import {
  getCompanyByNo,
  getCompanyByTaxNo,
  getCustomerByIdentity,
  getCustomerByNo,
} from "../../services/CustomerService";
import { toastOptions } from "../../Constants";
export default function ClientUpdate() {
  const [individualOrInstitutional, setIndividualOrInstitutional] =
    useState(true);
  const individual = () => {
    if (individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };
  const institutional = () => {
    if (!individualOrInstitutional) return;
    setIndividualOrInstitutional(!individualOrInstitutional);
  };

  const [client, setClient] = useState({});
  const [customerNo, setCustomerNo] = useState();
  const [identity, setIdentity] = useState();

  const fetchIndividualClient = () => {
    if (customerNo !== undefined && customerNo !== "") {
      getCustomerByNo(customerNo).then((res) => {
        if (!res.data.success) {
          toast.error("Müşteri getirilemedi!!", toastOptions);
          return;
        }
        if (!res.data.data) {
          toast.error("Müşteri getirilemedi!!", toastOptions);
          return;
        }
        setClient(res.data.data);
        toast.success("Müşteri getirildi", toastOptions);
      });
      return;
    }
    if (identity === undefined && identity === "") return;
    getCustomerByIdentity(identity).then((res) => {
      if (!res.data.success) {
        toast.error("Müşteri getirilemedi!!", toastOptions);
        return;
      }
      if (!res.data.data) {
        toast.error("Müşteri getirilemedi!!", toastOptions);
        return;
      }
      setClient(res.data.data);
      toast.success("Müşteri getirildi", toastOptions);
    });
  };

  const fetchInstitutionalClient = () => {
    if (customerNo !== undefined && customerNo !== "") {
      getCompanyByNo(customerNo).then((res) => {
        if (!res.data.success) {
          toast.error("Müşteri getirilemedi!!", toastOptions);
          return;
        }
        if (!res.data.data) {
          toast.error("Müşteri getirilemedi!!", toastOptions);
          return;
        }
        setClient(res.data.data);
        toast.success("Müşteri getirildi", toastOptions);
      });
      return;
    }
    if (identity === undefined && identity === "") return;
    getCompanyByTaxNo(identity).then((res) => {
      if (!res.data.success) {
        toast.error("Müşteri getirilemedi!!", toastOptions);
        return;
      }
      if (!res.data.data) {
        toast.error("Müşteri getirilemedi!!", toastOptions);
        return;
      }
      setClient(res.data.data);
      toast.success("Müşteri getirildi", toastOptions);
    });
  };

  const fetchUser = () => {
    if (individualOrInstitutional) {
      fetchIndividualClient();
      return;
    }
    fetchInstitutionalClient();
  };

  return (
    <UserLayout headerText={"MÜŞTERİ GÜNCELLEME"}>
      <div className="update-client client-wrapper">
        <div className="client-buttons">
          <div className="addorremove-buttons">
            <Button
              disabled
              style={{ width: 265, color: "white" }}
              className={`addorremove-button active`}
              variant="contained"
            >
              GÜNCELLE
            </Button>
          </div>
          <div className="ioi-buttons">
            <Button
              onClick={individual}
              className={`ioi-button ${
                individualOrInstitutional ? "active" : ""
              }`}
              variant="contained"
            >
              BİREYSEL
            </Button>
            <Button
              onClick={institutional}
              className={`ioi-button ${
                !individualOrInstitutional ? "active" : ""
              }`}
              variant="contained"
            >
              KURUMSAL
            </Button>
          </div>
        </div>
        <div className="client-update customer">
          <div className="input-wrapper">
            <div className="text">Müşteri Numarası</div>
            <TextField
              className="text-field"
              value={customerNo}
              onChange={(event) => setCustomerNo(event.target.value)}
              size="small"
              sx={{ m: 1, width: "35ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "1" }}
            />
          </div>
          <div className="break"></div>
          <div className="input-wrapper">
            <div className="text">
              {individualOrInstitutional ? "TCKimlik" : "VKN"}
            </div>
            <TextField
              className="text-field"
              value={identity}
              onChange={(event) => setIdentity(event.target.value)}
              size="small"
              sx={{ m: 1, width: "35ch" }}
              variant="outlined"
              inputProps={{ tabIndex: "2" }}
            />
          </div>
        </div>
        <Button
          onClick={fetchUser}
          style={{
            width: "300px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <PersonIcon style={{ marginRight: "15px", fontSize: "30px" }} />
          }
          size="large"
          variant="contained"
        >
          MÜŞTERİ GETİR
        </Button>

        {individualOrInstitutional ? (
          <UpdateIndividualClient client={client} />
        ) : (
          <UpdateInstitutionalClient client={client} />
        )}
      </div>
      <ToastContainer />
    </UserLayout>
  );
}
