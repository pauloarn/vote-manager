package com.nt.votemanager.client;

import com.nt.votemanager.exceptions.ExternalApiErrorException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "validate-cpf", url = "${nt.urls.validate-api-url}/users")
public interface ValidateCPFClient {
    @RequestMapping(method = RequestMethod.POST, value = "/{cpf}", produces = "application/json")
    String validateUserCPF(@PathVariable("cpf") String cpf) throws ExternalApiErrorException;
}
