
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	//NO esta corregida
	@Query("select count(r)*1.0/(select(a) from Application a) from Application r where r.status=1")
	public double pendingRatio();

	@Query("select a from Application a join a.fixUpTask f where f.customer.id = ?1")
	public Collection<Application> findAllCustomerApplication(Integer id);

	@Query("select a from Application a where a.handyWorker.id = ?1")
	public Collection<Application> getMyApplications(int handyWorkerId);
}
