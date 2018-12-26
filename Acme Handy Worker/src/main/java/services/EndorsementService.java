
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import security.LoginService;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	@Autowired
	private EndorsementRepository	endorsementRepository;


	// ---------- Simple CRUD methods ----------

	public Endorsement create() {

		//		final int id_user = LoginService.getPrincipal().getId();
		//		final Actor actor = this.actorService.getActorByUserAccount(id_user);

		final Endorsement e = new Endorsement();
		e.setComments(new HashSet<String>());
		e.setCustomerReceiver(null);
		e.setCustomerSender(null);
		e.setHandyWorkerReceiver(null);
		e.setHandyWorkerSender(null);
		e.setMoment(new Date());
		return e;
	}

	public Collection<Endorsement> findAll() {
		return this.endorsementRepository.findAll();
	}
	public Collection<Endorsement> myEndorsements(final int actorId) {
		return this.endorsementRepository.myEndorsements(actorId);
	}
	public Endorsement findOne(final int endorsementId) {
		return this.endorsementRepository.findOne(endorsementId);
	}
	public Endorsement save(final Endorsement e) {
		final Integer userAccountId = LoginService.getPrincipal().getId();
		Assert.isTrue(e.getCustomerSender().getUserAccount().equals(userAccountId) || e.getHandyWorkerSender().equals(userAccountId));
		Assert.isTrue((e.getCustomerReceiver() == null && e.getHandyWorkerReceiver() != null) || (e.getHandyWorkerReceiver() == null && e.getCustomerReceiver() != null));
		Assert.isTrue((e.getCustomerSender() == null && e.getHandyWorkerSender() != null) || (e.getHandyWorkerSender() == null && e.getCustomerSender() != null));
		return this.endorsementRepository.save(e);
	}
	public void delete(final Endorsement endorsement) {
		final Integer userAccountId = LoginService.getPrincipal().getId();
		final Collection<Endorsement> endorsements = this.myEndorsements(userAccountId);
		Assert.isTrue(endorsements.contains(endorsement), "EndorsementService.delete -> You mustn't delete this endorsment.");
		this.endorsementRepository.delete(endorsement);
	}
}
