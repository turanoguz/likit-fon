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

export default function AddRoleUser() {
  const [company, setCompany] = useState({ title: "Şirketi", id: 1 });
  const [branch, setBranch] = useState({ title: "şubesi", id: 1 });
  const [companies, setCompanies] = useState([]);
  const [branches, setBranches] = useState([{ title: "Genel Şirket", id: -1 }]);
  const [workers, setWorkers] = useState([{ title: "İRFAN", id: 1 }]);
  const [checkboxes, setCheckboxes] = useState([
    { title: "role1", id: 1 },
    { title: "role2", id: 2 },
    { title: "role3", id: 3 },
  ]);
  const schema = yup.object().shape({
    option: yup.array(),
  });

  const { register, handleSubmit } = useForm({
    mode: "all",
    resolver: yupResolver(schema),
  });
  const submit = async (data) => {
    console.log(data.roles);
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
                  control={<Checkbox {...register("roles")} />}
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
            width: "320px",
            height: "60px",
            fontSize: "20px",
          }}
          startIcon={
            <SaveIcon style={{ marginRight: "15px", fontSize: "30px" }} />
          }
          size="large"
          variant="contained"
        >
          ÇALIŞANA ROL EKLE
        </Button>
      </form>
    </>
  );
}
