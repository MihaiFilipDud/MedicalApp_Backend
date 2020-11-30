package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.AccountDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Person;

public class AccountBuilder {

    private AccountBuilder() {
    }

    public static AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(account.getUsername(), account.getPassword());
    }



    public static Account toEntity(AccountDTO accountDTO){
        return new Account(accountDTO.getUsername(), accountDTO.getUsername(), accountDTO.getPerson());
    }


}

