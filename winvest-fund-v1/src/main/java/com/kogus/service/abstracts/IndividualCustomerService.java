package com.kogus.service.abstracts;


import com.kogus.dto.IndividualCustomerRequest;
import com.kogus.entitiy.IndividualCustomer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IndividualCustomerService {

    ResponseEntity<Result> addIndividualCustomer(IndividualCustomerRequest customerRequest);

    boolean checkIfIdentificationNumberCustomer(IndividualCustomerRequest customerRequest);
    
    ResponseEntity<DataResult<List<IndividualCustomer>>> getAll();
    
    ResponseEntity<DataResult<IndividualCustomer>> getByIdentificationNumber(String identificationNumber);
    
    ResponseEntity<Result> updateIndividualCustomer(Integer id, IndividualCustomerRequest customerRequest);
    
    ResponseEntity<Result> addCash(String identityNumber, double amount);

    ResponseEntity<Result> cashTransfer(String identityNumber, double amount);
    
    ResponseEntity<Result> setActive(String idenTificationNumber);
    
    ResponseEntity<DataResult<IndividualCustomer>> getByCustomerNubmer(String customerNo);
}
