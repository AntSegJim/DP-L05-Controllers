
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixUpTask;

@Repository
public interface FixUpTaskRepository extends JpaRepository<FixUpTask, Integer> {

	@Query("select f from FixUpTask f where f.customer.id = ?1")
	public Collection<FixUpTask> fixUpTasksCustomer(Integer id);

	@Query("select f.fixUpTask from Finder f where f.id = ?1")
	public Collection<FixUpTask> fixUpTasksByFinder(Integer finderId);

	@Query("select count(f.customer) from FixUpTask f group by f.customer.id order by count(f.customer) ASC")
	public Collection<Integer> maxMinAvgDevFixUpTask();

	@Query("select f.ticker from FixUpTask f")
	public Collection<String> allTickerInFixUpTask();
}
