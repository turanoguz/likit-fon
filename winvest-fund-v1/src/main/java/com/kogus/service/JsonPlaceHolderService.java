package com.kogus.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kogus.entitiy.Fund;
import com.kogus.repository.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JsonPlaceHolderService {

    private final RestTemplate restTemplate;
    String apiKey = "XvusCI7S4hyr008JUcPyiAdMSzSHKmWa99VYFXeU";
    String apiUrl = "https://apitest.infina.com.tr/infina-services/rest/srv/v1.1/FonKunye.v2";

    private final FundRepository fundRepository;

    public static List<String> getFundCodes() {
        List<String> fundCodes = new ArrayList<>();
        fundCodes.add("AAL");
        fundCodes.add("AC4");
        fundCodes.add("BGP");
        fundCodes.add("DLY");
        fundCodes.add("EIL");
        fundCodes.add("FIL");
        fundCodes.add("GO6");
        fundCodes.add("HSL");
        fundCodes.add("HVT");
        fundCodes.add("HYV");
        fundCodes.add("ICE");
        fundCodes.add("IDL");
        fundCodes.add("IJV");
        fundCodes.add("IRY");
        fundCodes.add("KIE");
        fundCodes.add("KLU");
        fundCodes.add("NRG");
        fundCodes.add("NVB");
        fundCodes.add("NZT");
        fundCodes.add("PJL");
        fundCodes.add("PPN");
        fundCodes.add("PPZ");
        fundCodes.add("PRU");
        fundCodes.add("RPP");
        fundCodes.add("TCB");
        fundCodes.add("UPP");
        fundCodes.add("ZBJ");
        fundCodes.add("AHU");
        fundCodes.add("AK2");
        fundCodes.add("APT");
        fundCodes.add("ARE");
        fundCodes.add("AUT");
        fundCodes.add("AYR");
        fundCodes.add("DBB");
        fundCodes.add("DBK");
        fundCodes.add("DBZ");
        fundCodes.add("FI3");
        fundCodes.add("FIT");
        fundCodes.add("FPK");
        fundCodes.add("FYO");
        fundCodes.add("GA1");
        fundCodes.add("GBL");
        fundCodes.add("GTF");
        fundCodes.add("GYK");
        fundCodes.add("HPT");
        fundCodes.add("HST");
        fundCodes.add("HVK");
        fundCodes.add("IFV");
        fundCodes.add("IST");
        fundCodes.add("KRC");
        fundCodes.add("NJR");
        fundCodes.add("NZH");
        fundCodes.add("OBI");
        fundCodes.add("OKP");
        fundCodes.add("OKT");
        fundCodes.add("OSD");
        fundCodes.add("OSL");
        fundCodes.add("TBT");
        fundCodes.add("TBV");
        fundCodes.add("TDG");
        fundCodes.add("TI6");
        fundCodes.add("TLE");
        fundCodes.add("TOT");
        fundCodes.add("TPF");
        fundCodes.add("TZT");
        fundCodes.add("YBS");
        fundCodes.add("YOT");
        return fundCodes;
    }

    public void fetchAndSaveFundData() {
        for (String fundCode : getFundCodes()) {
            String url = apiUrl + "?fund_code=" + fundCode + "&api_key=" + apiKey;
            String responseBody = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                JsonNode obj = objectMapper.readTree(responseBody);
                Optional<Fund> existingFundOptional = fundRepository.findByFundCode(obj.get("result").get("data").get("FundCode").asText());
                if (existingFundOptional.isPresent()) {
                    Fund existingFund = existingFundOptional.get();
                    existingFund.setFundName(obj.get("result").get("data").get("FundDetail").get("description").asText());
                    existingFund.setFundPrice(obj.get("result").get("data").get("FundDetail").get("price").asDouble());
                    existingFund.setFundFounder(obj.get("result").get("data").get("FundDetail").get("fund_founder").asText());
                    existingFund.setFundType(obj.get("result").get("data").get("FundDetail").get("type").asText());
                    existingFund.setFundDate(dateFormat.parse(obj.get("result").get("data").get("Date").asText()));
                    fundRepository.save(existingFund);
                } else {
                    Fund newFund = new Fund();
                    newFund.setFundCode(obj.get("result").get("data").get("FundCode").asText());
                    newFund.setFundName(obj.get("result").get("data").get("FundDetail").get("description").asText());
                    newFund.setFundPrice(obj.get("result").get("data").get("FundDetail").get("price").asDouble());
                    newFund.setFundFounder(obj.get("result").get("data").get("FundDetail").get("fund_founder").asText());
                    newFund.setFundType(obj.get("result").get("data").get("FundDetail").get("type").asText());
                    newFund.setFundDate(dateFormat.parse(obj.get("result").get("data").get("Date").asText()));
                    fundRepository.save(newFund);
                    System.out.println("yeni yazdım");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
