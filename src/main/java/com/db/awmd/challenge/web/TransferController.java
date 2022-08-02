package com.db.awmd.challenge.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.Transfer;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.LogException;
import com.db.awmd.challenge.service.AccountsService;
import com.db.awmd.challenge.service.TransferService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/transfer")
@Slf4j
public class TransferController {
	
	private final TransferService transferService ;
	
	@Autowired
	  public TransferController(TransferService transferService) {
	    this.transferService = transferService;
	  }
	
	  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<Object> transferAccount(@RequestBody @Valid Transfer transfer) {
	    log.info("Transferring balance {}", transfer);

	    try {
	    this.transferService.transferAmount(transfer);
	    } catch (LogException log) {
	      return new ResponseEntity<>(log.getMessage(), HttpStatus.BAD_REQUEST);
	    }

	    return new ResponseEntity<>(HttpStatus.CREATED);
	  }

}
