
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorsement;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Integer> {

	@Query("select e from Endorsement e where (e.handyWorkerSender.userAccount = ?1 and e.handyWorkerReceiver.userAccount = ?1) or (e.customerSender.userAccount = ?1 and e.customerReceiver.userAccount = ?1)")
	public Collection<Endorsement> myEndorsements(Integer userAccountId);
}
