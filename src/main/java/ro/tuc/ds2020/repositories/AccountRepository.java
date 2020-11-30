package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2020.entities.Account;
import ro.tuc.ds2020.entities.Patient;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {


}
