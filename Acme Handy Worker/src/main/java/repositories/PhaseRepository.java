
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Phase;

@Repository
public interface PhaseRepository extends JpaRepository<Phase, Integer> {

	//En ningún requisito pide que listemos las phases por esto esta comentado
	//	@Query("select c from Phase c join c.Application f where f.handyWorker.id = ?1")
	//	public Collection<Phase> findAllHandyWorkerPhase(Integer id);

}
