
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import security.UserAccount;
import domain.Sponsorship;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorshipRepository	SRepo;
	@Autowired
	private CreditCardService		CCService;
	@Autowired
	private SponsorService			sponsorService;
	@Autowired
	private ActorService			actorS;


	public Sponsorship create() {
		final Sponsorship sponsorship = new Sponsorship();
		sponsorship.setUrlBanner("");
		sponsorship.setLinkTargetPage("");
		sponsorship.setCreditCard(this.CCService.create());
		sponsorship.setSponsor(this.sponsorService.create());

		return sponsorship;
	}

	//listing
	public Collection<Sponsorship> findAll() {
		return this.SRepo.findAll();
	}
	public Sponsorship findOne(final int sponsorshipId) {
		return this.SRepo.findOne(sponsorshipId);
	}

	//updating
	public Sponsorship save(final Sponsorship sponsorship) {
		final UserAccount user = this.actorS.getActorLogged().getUserAccount();
		Assert.isTrue(user.getAuthorities().iterator().next().getAuthority().equals("SPONSOR"));
		Assert.isTrue(sponsorship != null && sponsorship.getLinkTargetPage() != null && sponsorship.getLinkTargetPage() != "" && sponsorship.getUrlBanner() != null && sponsorship.getUrlBanner() != "" && sponsorship.getCreditCard() != null
			&& sponsorship.getSponsor() != null);
		return this.SRepo.save(sponsorship);
	}

	//deleting
	public void delete(final Sponsorship sponsorship) {
		this.SRepo.delete(sponsorship);
	}

	public Collection<Sponsorship> findAllMySponsorships(final Integer sponsorId) {
		return this.SRepo.allMySponsorships(sponsorId);
	}
}
