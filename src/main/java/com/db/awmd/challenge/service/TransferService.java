package com.db.awmd.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.Transfer;
import com.db.awmd.challenge.exception.LogException;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;

@Service
public class TransferService {
	
	 @Getter
	  private final AccountsRepository accountsRepository;
	 


	  @Autowired
	  public TransferService(AccountsRepository accountsRepository) {
	    this.accountsRepository = accountsRepository;
	  }
	  
	 
	  private NotificationService notification ;
	  
	  @Autowired
	  public void setNotificationService(NotificationService notification) {
		  this.notification =  notification ;
		  
	  }
	  
	  
	  

	  
	  public void transferAmount(Transfer transfer) throws LogException{
		  
		  Account fromAccount = this.accountsRepository.getAccount(transfer.getAccountFrom()) ;
		  Account toAccount = this.accountsRepository.getAccount(transfer.getAccountTo()) ;
		  
		  this.accountsRepository.updateAccount(fromAccount, transfer.getAmountToTransfer().negate()) ;
		  this.accountsRepository.updateAccount(toAccount, transfer.getAmountToTransfer()) ;
		  
		  notification.notifyAboutTransfer(fromAccount, "Amount-"+transfer.getAmountToTransfer()+" has been trasferred to Account "+toAccount.getAccountId()) ;;
		  notification.notifyAboutTransfer(toAccount, "Amount-"+transfer.getAmountToTransfer()+" has been trasferred from Account "+fromAccount.getAccountId()) ;;


}
}
