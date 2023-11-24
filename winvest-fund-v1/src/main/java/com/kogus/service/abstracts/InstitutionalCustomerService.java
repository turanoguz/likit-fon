package com.kogus.service.abstracts;


import com.hazelcast.spi.impl.operationservice.impl.responses.Response;
import com.kogus.dto.InstitutionalCustomerRequest;
import com.kogus.dto.InstitutionalCustomerResponse;
import com.kogus.entitiy.IndividualCustomer;
import com.kogus.entitiy.InstitutionalCustomer;
import com.kogus.result.DataResult;
import com.kogus.result.Result;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface InstitutionalCustomerService {

    ResponseEntity<Result> addInstitutionalCustomer(InstitutionalCustomerRequest customerRequest);

    boolean checkIfIdentificationNumberCustomer(InstitutionalCustomerRequest customerRequest);
    
    ResponseEntity<DataResult<List<InstitutionalCustomer>>> getAll();
    
    ResponseEntity<DataResult<InstitutionalCustomer>> getBytaxNo(String taxNo);
    
    ResponseEntity<Result> updateInstitutionalCustomer(Integer id, InstitutionalCustomerResponse customerResponse);
    
    public ResponseEntity<Result> addCash(@RequestBody String customerTaxNo, double amount);

    ResponseEntity<Result> cashTransfer(String customerTaxNo, double amount);
    
    public ResponseEntity<Result> setActive(String taxNo);
    
    public ResponseEntity<DataResult<InstitutionalCustomer>> getByCustomerNubmer(String customerNo);
}
