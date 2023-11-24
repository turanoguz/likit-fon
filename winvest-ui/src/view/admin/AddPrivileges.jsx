import React, { useState } from "react";
import SaveIcon from "@mui/icons-material/Save";
import {
  TextField,
  Button,
  Autocomplete,
  FormControlLabel,
  Checkbox,
  FormGroup,
} from "@mui/material";
import { useForm } from "react-hook-form";

import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

export default function AddPrivileges() {
  const [company, setCompany] = useState({ title: "Şirketi", id: 1 });
  const [branch, setBranch] = useState({ title: "şubesi", id: 1 });
  const [companies, setCompanies] = useState([]);
  const [branches, setBranches] = useState([{ title: "Genel Şirket", id: -1 }]);
  const [workers, setWorkers] = useState([{ title: "ben", id: 1 }]);
  const [checkboxes, setCheckboxes] = useState([
    { title: "ekle", id: 1 },
    { title: "çıkar", id: 2 },
    { title: "sil", id: 3 },
    { title: "güncelle", id: 4 },
    { title: "ekle", id: 5 },
    { title: "çıkar", id: 6 },
    { title: "sil", id: 7 },
    { title: "güncelle", id: 8 },
    { title: "ekle", id: 9 },
    { title: "çıkar", id: 10 },
    { title: "sil", id: 11 },
    { title: "güncelle", id: 12 },
    { title: "ekle", id: 13 },
    { title: "çıkar", id: 14 },
    { title: "sil", id: 15 },
    { title: "güncelle", id: 16 },
    { title: "SEVİLİYORSUN", id: 17 },
    { title: "KALP", id: 18 },
    { title: "İRFAN", id: 19 },
  ]);
  const schema = yup.object().shape({
    option: yup.array(),
  });

  const { register, handleSubmit } = useForm({
    mode: "all",
    resolver: yupResolver(schema),
  });
  const submit = async (data) => {
    console.log(data.privileges);
  };

  return (
    <>
      <div className="role customer">
        <div className="input-wrapper">
          <div className="text">Şirket</div>
          <Autocomplete
            className="text-field"
            size="small"
            value={company}
            options={companies}
            disabled
            getOptionLabel={(option) => option.title}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            //onChange={(event, newValue) => onChange(newValue)}
            disablePortal
            sx={{ m: 1, width: 300 }}
            renderInput={(params) => <TextField {...params} />}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Şube</div>
          <Autocomplete
            className="text-field"
            disabled
            size="small"
            value={branch}
            options={branches}
            isOptionEqualToValue={(option, value) =>
              option.value === value.value
            }
            getOptionLabel={(option) => option.title}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            //onChange={(event, newValue) => onChange(newValue)}
            disablePortal
            sx={{ m: 1, width: 300 }}
            renderInput={(params) => <TextField {...params} />}
          />
        </div>
        <div className="input-wrapper">
          <div className="text">Çalışan</div>
          <Autocomplete
            className="text-field"
            size="small"
            options={workers}
            getOptionLabel={(option) => option.title}
            //onInputChange={(event, newInputValue) => searchFund(newInputValue)}
            //onChange={(event, newValue) => onChange(newValue)}
            disablePortal
            sx={{ m: 1, width: 300 }}
            renderInput={(params) => <TextField {...params} />}
          />
        </div>
      </div>
      <form className="role-form" onSubmit={handleSubmit(submit)}>
        <div className="customer">
          <FormGroup className="privileges">
            {checkboxes.map((value) => {
              return (
                <FormControlLabel
                  control={<Checkbox {...register("privileges")} />}
                  key={value.id}
                  label={value.title}
                  value={JSON.stringify(value)}
                />
              );
            })}
          </FormGroup>
        </div>
        <Button
          type="submit"
          onClick={() => handleSubmit(submit)}
          style={{
            width: "250px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
          }
          size="large"
          variant="contained"
        >
          YETKİ EKLE
        </Button>
      </form>
    </>
  );
}
