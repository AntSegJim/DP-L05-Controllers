
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select c from Customer c where c.userAccount.id = ?1")
	public Customer customerUserAccount(Integer id);

	@Query("select f.customer from FixUpTask f join f.application a where a.handyWorker.id = ?1")
	public Collection<Customer> getCustomerForWhomItIsWorked(int handyWorkerId);
}
/**
 * 
 * 
 The listing of customers who have published at least 10% more fix-up tasks than the average, ordered by number of applications. FALTA ORDER BY
 * 
 * select e.name from Customer e where
 * ((select sum(a.fixUpTask.size)*1.0/(select count(h) from Customer h) from Customer a)) * 0.1 +
 * ((select sum(a.fixUpTask.size)*1.0/(select count(h)
 * from Customer h) from Customer a))
 * < e.fixUpTask.size;
 * 
 * ----------------------------------------------
 * The top-three customers in terms of complaints.
 * 
 * 
 * 
 * **/
