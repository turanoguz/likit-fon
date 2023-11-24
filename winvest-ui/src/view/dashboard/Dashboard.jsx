import React, { useEffect, useState } from "react";
import { extractClaimsFromJWT } from "../../JwtService";
import UserLayout from "../../layout/user/UserLayout";
import { DataGrid, trTR } from "@mui/x-data-grid";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";
import { Line } from "react-chartjs-2";
import "./Dashboard.css";
import { getAllFunds, getMostValuedFund } from "../../services/FundService";
export default function Dashboard() {
  const [rows, setRows] = useState([]);
  const [funds, setFunds] = useState([]);
  useEffect(() => {
    getAllFunds()
      .then((res) => {
        setRows(res.data.data.map((value) => value));
      })
      .catch((err) => {
        console.log("hata");
      });
    getMostValuedFund().then((res) => {
      setFunds(res.data);
    });
  }, []);

  ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend
  );

  const days = [
    "PAZAR",
    "PAZARTESİ",
    "SALI",
    "ÇARŞAMBA",
    "PERŞEMBE",
    "CUMA",
    "CUMARTESİ",
  ];

  const getLabels = () => {
    const labels = [];
    let lastFundDate =
      funds.length === 0
        ? new Date()
        : new Date(funds[funds.length - 1].fundDate);
    for (let index = 6; index >= funds.length; index--) {
      labels.push(
        days[new Date(lastFundDate.getTime() - index * 86400000).getDay()]
      );
      datasets[0].data.push(null);
    }
    funds.forEach((value) => {
      labels.push(days[new Date(value.fundDate).getDay()]);
      datasets[0].data.push(value.fundPrice);
    });
    return labels;
  };

  const datasets = [
    {
      label: "TL",
      data: [],
      borderColor: "rgb(255, 99, 132)",
      backgroundColor: "rgba(255, 99, 132, 0.5)",
    },
  ];
  const columns = [
    {
      field: "fundCode",
      headerName: "Fon Kodu",
      width: 100,
      sortable: false,
    },
    { field: "fundName", headerName: "Fon Adı", width: 450, sortable: false },
    { field: "fundType", headerName: "Fon Türü", width: 100, sortable: false },
    {
      field: "fundPrice",
      headerName: "Fon Fiyatı",
      width: 130,
      sortable: false,
    },

    {
      field: "fundFounder",
      headerName: "Fon Kurucusu",
      sortable: false,
      width: 250,
    },
    {
      field: "currencyType",
      headerName: "Doviz Türü",
      width: 150,
      sortable: false,
      filterable: false,
      valueGetter: (params) => "TL",
    },
  ];
  // let user = extractClaimsFromJWT();
  return (
    <UserLayout headerText={"Ana Sayfa"}>
      <div
        className="dashboard-layout"
        style={{
          background:
            "linear-gradient(180deg, #9e9a9a 0%, rgba(0, 0, 0, 0) 100%)",
          height: "50%",
          width: "100%",
        }}
      >
        <span className="fund-text">FONLAR</span>
        <DataGrid
          localeText={trTR.components.MuiDataGrid.defaultProps.localeText}
          disableRowSelectionOnClick
          style={{ color: "black", userSelect: "none" }}
          rows={rows}
          columns={columns}
          initialState={{
            pagination: {
              paginationModel: { page: 0, pageSize: 50 },
            },
          }}
          pageSizeOptions={[50]}
        />
      </div>
      <div className="chart-wrapper">
        <span style={{ marginTop: "20px" }} className="fund-text">
          BUGÜN EN ÇOK ARTAN FONUN HAFTALIK DEĞİŞİMİ
        </span>
        <Line
          style={{ maxWidth: "100%" }}
          options={{
            scales: {
              y: {
                title: {
                  display: true,
                  text: "TL",
                  color: "black",
                  font: {
                    size: 20,
                  },
                },
              },
            },
            responsive: true,
            plugins: {
              legend: false,
              title: {
                display: true,
                text:
                  funds.length === 0
                    ? ""
                    : `${funds[0].fundName} (${funds[0].fundCode})`,
                color: "black",
                font: {
                  size: 20,
                },
              },
            },
          }}
          data={{ labels: getLabels(), datasets: datasets }}
        ></Line>
        <div style={{ height: "50px" }}></div>
      </div>
    </UserLayout>
  );
}
