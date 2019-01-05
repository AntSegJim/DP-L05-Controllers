
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.ActorRepository;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class Prueba {

	@Autowired
	private ActorRepository	actorRepository;


	@Test
	public void prueba() {
		final List<Actor> lista = this.actorRepository.actorSuspiciousFixUp("lo");

		System.out.println(lista.get(0));
	}

}
