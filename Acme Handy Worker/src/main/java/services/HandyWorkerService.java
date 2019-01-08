
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import security.Authority;
import security.UserAccount;
import domain.Application;
import domain.Endorsement;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;
	@Autowired
	private MessageBoxService		messageBoxService;
	@Autowired
	private FinderService			finderService;


	public HandyWorker create() {
		final HandyWorker h = new HandyWorker();
		h.setAddress("");
		h.setEmail("");
		h.setEndorseHWorker(new HashSet<Endorsement>());
		h.setMiddleName("");
		h.setName("");
		h.setNumberSocialProfiles(0);
		h.setPhone("");
		h.setPhoto("");
		h.setReceiveEndorseFromHWorker(new HashSet<Endorsement>());
		h.setScore(0);
		h.setSurname("");
		h.setIsBanned(0);
		h.setFinder(this.finderService.create());
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.HANDYWORKER);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		h.setUserAccount(user);

		h.setMakeHandyWorker("");
		h.setApplication(new HashSet<Application>());
		return h;
	}

	public Collection<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	public HandyWorker findOne(final int handyWorkerId) {
		return this.handyWorkerRepository.findOne(handyWorkerId);
	}

	public HandyWorker save(final HandyWorker h) {
		HandyWorker res = null;

		Assert.isTrue(h.getName() != null && h.getSurname() != null && h.getName() != "" && h.getSurname() != "" && h.getUserAccount() != null && h.getEmail() != null && h.getEmail() != "", "HandyWorkerService.save -> Name or Surname invalid");

		if (h.getId() == 0)
			h.setMakeHandyWorker(h.getName() + " " + h.getMiddleName() + " " + h.getSurname());

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern pattern = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = pattern.matcher(h.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(h.getEmail());
		Assert.isTrue(matcherEmail1.matches() == true || matcherEmail2.matches() == true, "CustomerService.save -> Correo inválido");

		if (h.getPhone() != "" || h.getPhone() != null) {
			final String regexTelefono = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$";
			final Pattern patternTelefono = Pattern.compile(regexTelefono);
			final Matcher matcherTelefono = patternTelefono.matcher(h.getPhone());
			Assert.isTrue(matcherTelefono.find() == true, "CustomerService.save -> Telefono inválido");
		}

		//NUEVO
		Assert.isTrue(h.getUserAccount().getUsername() != null && h.getUserAccount().getUsername() != "");
		Assert.isTrue(h.getUserAccount().getPassword() != null && h.getUserAccount().getPassword() != "");

		if (h.getId() == 0) {
			final Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(h.getUserAccount().getPassword(), null);
			final UserAccount user = h.getUserAccount();
			user.setPassword(hash);

		}
		Assert.isTrue(h.getIsBanned() == 0 || h.getIsBanned() == 1);

		if (h.getIsBanned() == 1) {
			final Collection<Authority> result = new ArrayList<Authority>();
			Authority authority;
			authority = new Authority();
			authority.setAuthority(Authority.HANDY_WORKER_BAN);
			result.add(authority);

			h.getUserAccount().setAuthorities(result);
		} else {
			final Collection<Authority> result = new ArrayList<Authority>();
			Authority authority;
			authority = new Authority();
			authority.setAuthority(Authority.HANDYWORKER);
			result.add(authority);

			h.getUserAccount().setAuthorities(result);
		}

		this.finderService.save(h.getFinder());
		res = this.handyWorkerRepository.save(h);

		if (h.getId() == 0)
			this.messageBoxService.createMessageBoxSystem(res);

		return res;
	}

	public Collection<HandyWorker> handyWorkerMoreTentPercentApplicatonsAccepted() {
		return this.handyWorkerRepository.handyWorkerMoreTentPercentApplicatonsAccepted();
	}

	//	public Collection<HandyWorker> handyWorkerByUserAccount(final Integer userAccountId) {
	//		return this.handyWorkerByUserAccount(userAccountId);
	//	}

	//Añadido por jesus para un metodo en phaseService
	public HandyWorker handyWorkerUserAccount(final Integer id) {
		return this.handyWorkerRepository.handyWorkerUserAccount(id);
	}

	public static Double score(final Integer palabrasBuenas, final Integer palabrasMalas) {
		final Double x = (1.0 / ((double) palabrasBuenas + (double) palabrasMalas));
		final Double res = palabrasBuenas * x - palabrasMalas * x;
		return res;

	}

	public Collection<HandyWorker> getHandyWorkerInvolveInAnyOfHisFixUpTask(final int customerId) {
		return this.handyWorkerRepository.getHandyWorkerInvolveInAnyOfHisFixUpTask(customerId);
	}

}
