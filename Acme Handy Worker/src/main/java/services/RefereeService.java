
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.ProfileSocialNetwork;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	@Autowired
	private RefereeRepository	refereeRepository;
	@Autowired
	private MessageBoxService	messageBoxService;


	//Metodos CRUD

	public Referee create() {
		final Referee res = new Referee();

		res.setName("");
		res.setMiddleName("");
		res.setSurname("");
		res.setPhoto("");
		res.setEmail("");
		res.setPhone("");
		res.setAddress("");
		res.setNumberSocialProfiles(0);
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.REFEREE);
		user.getAuthorities().add(ad);
		user.setUsername("");
		user.setPassword("");
		res.setUserAccount(user);

		res.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		return res;
	}

	//	public Referee create(final String name, final String middleName, final String surname, final String photo, final String email, final String phone, final String address, final Integer numberSocialProfiles) {
	//		final Referee referee = new Referee();
	//		referee.setName(name);
	//		referee.setMiddleName(middleName);
	//		referee.setSurname(surname);
	//		referee.setPhoto(photo);
	//		referee.setEmail(email);
	//		referee.setPhone(phone);
	//		referee.setAddress(address);
	//		referee.setNumberSocialProfiles(numberSocialProfiles);
	//
	//		return referee;
	//	}

	//listing
	public Collection<Referee> findAll() {
		return this.refereeRepository.findAll();
	}

	public Referee findOne(final int refereeId) {
		return this.refereeRepository.findOne(refereeId);
	}

	//updating
	public Referee save(final Referee r) {
		final UserAccount userLoged = LoginService.getPrincipal();
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("ADMIN"));

		Referee res = null;

		Assert.isTrue(r != null && r.getName() != null && r.getSurname() != null && r.getName() != "" && r.getSurname() != "" && r.getUserAccount() != null && r.getEmail() != null && r.getEmail() != "", "RefereeService.save -> Name or Surname invalid");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(r.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(r.getEmail());
		Assert.isTrue(matcherEmail1.find() == true || matcherEmail2.find() == true, "CustomerService.save -> Correo inválido");

		if (r.getPhone() != "" || r.getPhone() != null) {
			final String regexTelefono = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$";
			final Pattern patternTelefono = Pattern.compile(regexTelefono);
			final Matcher matcherTelefono = patternTelefono.matcher(r.getPhone());
			Assert.isTrue(matcherTelefono.find() == true, "CustomerService.save -> Telefono no valido");
		}

		//NUEVO
		Assert.isTrue(r.getUserAccount().getUsername() != null && r.getUserAccount().getUsername() != "");
		Assert.isTrue(r.getUserAccount().getPassword() != null && r.getUserAccount().getPassword() != "");

		final Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		final String hash = encoder.encodePassword(r.getUserAccount().getPassword(), null);
		final UserAccount user = r.getUserAccount();
		user.setPassword(hash);

		res = this.refereeRepository.save(r);
		//		final MessageBox mb1 = this.messageBoxService.create();
		//		mb1.setName("inbox");
		//		mb1.setActor(res);
		//		final MessageBox mb2 = this.messageBoxService.create();
		//		mb2.setName("outbox");
		//		mb2.setActor(res);
		//		final MessageBox mb3 = this.messageBoxService.create();
		//		mb3.setName("spambox");
		//		mb3.setActor(res);
		//		final MessageBox mb4 = this.messageBoxService.create();
		//		mb4.setName("trashbox");
		//		mb4.setActor(res);
		this.messageBoxService.createMessageBoxSystem(res);

		return res;
	}

	//------------------------Other business methods---------------------
	public Referee refereeByUserAccount(final Integer userAccountId) {
		return this.refereeRepository.refereeUserAccount(userAccountId);
	}

}
