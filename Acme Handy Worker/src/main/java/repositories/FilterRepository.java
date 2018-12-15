
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Filter;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Integer> {

}
