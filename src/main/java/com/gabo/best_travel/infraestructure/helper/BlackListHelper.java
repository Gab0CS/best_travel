package com.gabo.best_travel.infraestructure.helper;

import org.springframework.stereotype.Component;

import com.gabo.best_travel.util.exceptions.ForbiddenCustomerException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class BlackListHelper {

    public void isInBlackListCustomer(String customerId){
        if(customerId.equals("BBMB771012HMCRR022")){
            throw new ForbiddenCustomerException();
        }
    }
    
}
