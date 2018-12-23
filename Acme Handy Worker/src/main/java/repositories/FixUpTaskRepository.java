
package repositories;

import java.util.Collection;
import java.util.Date;

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

	@Query("select f from FixUpTask f where (locate(?1,f.ticker) != 0 or locate(?2,f.description) != 0 or locate(?3,f.address) != 0) and f.moment between ?4 and ?5 and f.maximunPrice between ?6 and ?7 and locate(?8,f.category.name) != 0 and locate(?9,f.warranty.title) != 0")
	public Collection<FixUpTask> filterFixUpTask(String ticker, String description, String address, Date fi, Date ff, Double lp, Double hp, String c, String w);

	@Query(
		value = "select * from Fix_up_task f where (locate(?1,f.ticker) != 0 or locate(?2,f.description) != 0 or locate(?3,f.address) != 0) and f.moment between ?4 and ?5 and f.maximun_price between ?6 and ?7 and locate(?8,f.category) != 0 and locate(?9,f.warranty) != 0 LIMIT ?10",
		nativeQuery = true)
	public Collection<FixUpTask> filterFixUpTask2(String ticker, String description, String address, Date fi, Date ff, Double lp, Double hp, String c, String w, Integer limite);

}
