package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.LogException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

  private final Map<String, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public void createAccount(Account account) throws DuplicateAccountIdException {
    Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
    if (previousAccount != null) {
      throw new DuplicateAccountIdException(
        "Account id " + account.getAccountId() + " already exists!");
    }
  }

  @Override
  public Account getAccount(String accountId) {
    return accounts.get(accountId);
  }

  @Override
  public void clearAccounts() {
    accounts.clear();
  }
  
  public void updateAccount(Account account, BigDecimal updateAmount) throws LogException
  {
	  if ((account.getBalance().add(updateAmount)).compareTo(BigDecimal.ZERO) < 0)
	  {
		  throw new LogException("Transfer Amount -"+ updateAmount+" can not be grater than avaialble amount -"+account.getBalance()) ;
	  }
	  
	  account.setBalance(account.getBalance().add(updateAmount)); 
	  
  }

}
