
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	public Actor getActorByUserAccount(Integer id);

	@Query(" select a from Actor a where a.email = ?1 ")
	public Actor getActorByEmail(String email);

	@Query("select distinct m.sender from Message m where m.body like %?1% or m.subject like %?1% or m.tag like %?1%")
	public List<Actor> actorSuspicious(String palabra);

	@Query("select a from Actor a where a.userAccount.username = ?1")
	public Actor getActoyByUsername(String s);

}
