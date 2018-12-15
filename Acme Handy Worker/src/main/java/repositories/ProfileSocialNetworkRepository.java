
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ProfileSocialNetwork;

@Repository
public interface ProfileSocialNetworkRepository extends JpaRepository<ProfileSocialNetwork, Integer> {

}
