import React, { useEffect, useState } from "react";
import { DataGrid, trTR } from "@mui/x-data-grid";
import { getAllInstitutionalClients } from "../../services/CustomerService";
import { getAllFundsCustomerByNo } from "../../services/FundService";

export default function InstitutionalClients() {
  const [clients, setClients] = useState([]);

  useEffect(() => {
    setClients([]);
    const innerFunc = async () => {
      const institutionalClients = await getAllInstitutionalClients();
      await institutionalClients.data.data.forEach(async (element, index) => {
        const client = { id: index };
        client.customerNo = element.customerNo;
        client.identity = element.customerTaxNo;
        client.companyName = element.companyName;
        const accountData = await getAllFundsCustomerByNo(element.customerNo);
        const account = accountData.data.data[0];
        client.balance = account.totalMoney;
        client.portfolio = account.totalFundPrice;
        client.funds = account.funds.map((value) => " " + value.fundCode);
        setClients((prev) => [...prev, client]);
      });
    };
    innerFunc();
  }, []);
  const columns = [
    {
      field: "customerNo",
      headerName: "Müşteri No",
      width: 150,
      sortable: false,
    },
    {
      field: "identity",
      headerName: "VKN",
      width: 150,
      sortable: false,
      valueGetter: (params) =>
        params.value.substring(0, 2) + "******" + params.value.substring(8, 10),
    },
    {
      field: "companyName",
      headerName: "Kurum Adı",
      sortable: false,
      width: 160,
    },
    {
      field: "balance",
      headerName: "Nakit Durumu (TL)",
      width: 180,
      sortable: false,
      filterable: false,
      valueGetter: (params) => params.value.toFixed(2),
    },
    {
      field: "portfolio",
      headerName: "Portföy Değeri (TL)",
      width: 180,
      sortable: false,
      filterable: false,
      valueGetter: (params) => params.value.toFixed(2),
    },
    {
      field: "funds",
      headerName: "Fonları",
      width: 350,
      sortable: false,
    },
  ];

  return (
    <div
      style={{
        background:
          "linear-gradient(180deg, #9e9a9a 0%, rgba(0, 0, 0, 0) 100%)",
        height: "90%",
        width: "100%",
      }}
    >
      <DataGrid
        localeText={trTR.components.MuiDataGrid.defaultProps.localeText}
        disableRowSelectionOnClick
        style={{ color: "black" }}
        rows={clients}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 10 },
          },
        }}
        pageSizeOptions={[10]}
      />
    </div>
  );
}
