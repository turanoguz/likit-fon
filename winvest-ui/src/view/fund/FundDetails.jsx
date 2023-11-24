import React, { useEffect, useState } from "react";
import "./FundDetails.css";
import { Autocomplete, TextField, MenuItem } from "@mui/material";
import UserLayout from "../../layout/user/UserLayout";
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
import { getAllFunds, getLogsFund } from "../../services/FundService";
export default function FundDetails() {
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
  const [funds, setFunds] = useState([]);
  const [selectedFund, setSelectedFund] = useState({
    fundName: "",
    fundCode: "",
  });
  const [selectedFundLogs, setSelectedFundLogs] = useState([]);

  const getLabels = () => {
    const labels = [];
    let lastFundDate =
      selectedFundLogs.length === 0
        ? new Date()
        : new Date(selectedFundLogs[selectedFundLogs.length - 1].fundDate);
    for (let index = 6; index >= selectedFundLogs.length; index--) {
      labels.push(
        days[new Date(lastFundDate.getTime() - index * 86400000).getDay()]
      );
      datasets[0].data.push(null);
    }
    selectedFundLogs.forEach((value) => {
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

  useEffect(() => {
    getAllFunds().then((res) => {
      setFunds(res.data.data.map((value) => value));
    });
  }, []);

  useEffect(() => {
    if (selectedFund.fundName === "") return;
    getLogsFund(selectedFund.id).then((res) => {
      setSelectedFundLogs(res.data);
    });
  }, [selectedFund]);

  return (
    <UserLayout headerText={"FON DETAYLARI"}>
      <div className="fundetails customer">
        <div className="input-wrapper">
          <div className="text">Fon Seç</div>
          <Autocomplete
            className="text-field"
            size="small"
            sx={{ m: 1, width: "50ch", marginRight: "20px" }}
            tabIndex={3}
            options={funds}
            getOptionLabel={(option) => option.fundName}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            onChange={(event, newValue) => {
              if (newValue === null) return;
              setSelectedFund(newValue);
            }}
            disablePortal
            renderInput={(params) => <TextField {...params} />}
          >
            {funds.map((value, index) => {
              return (
                <MenuItem key={value.id} value={value.id}>
                  {value.fundName}
                </MenuItem>
              );
            })}
          </Autocomplete>
        </div>
      </div>
      <div className="chart-wrapper">
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
                  selectedFund.fundName === ""
                    ? ""
                    : `${selectedFund.fundName} (${selectedFund.fundCode})`,
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
