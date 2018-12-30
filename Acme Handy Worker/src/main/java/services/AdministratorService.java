
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Category;
import domain.ProfileSocialNetwork;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	private AdministratorRepository	adminRepo;
	@Autowired
	private MessageBoxService		messageBoxService;


	public Administrator create() {
		final Administrator admin = new Administrator();
		admin.setName("");
		admin.setMiddleName("");
		admin.setSurname("");
		admin.setPhoto("");
		admin.setEmail("");
		admin.setPhone("");
		admin.setAddress("");
		admin.setIsBanned(0);
		admin.setNumberSocialProfiles(0);
		//PREGUNTAR
		final UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		final Authority ad = new Authority();
		ad.setAuthority(Authority.ADMIN);
		user.getAuthorities().add(ad);

		//NUEVO
		user.setUsername("");
		user.setPassword("");

		admin.setUserAccount(user);

		admin.setProfileSocialNetwork(new HashSet<ProfileSocialNetwork>());
		admin.setCategories(new HashSet<Category>());

		return admin;
	}

	//Listing
	public Collection<Administrator> findAll() {
		return this.adminRepo.findAll();
	}
	public Administrator findOne(final int adminId) {
		return this.adminRepo.findOne(adminId);
	}

	//Update
	public Administrator save(final Administrator admin) {
		final UserAccount userLoged = LoginService.getPrincipal();
		Assert.isTrue(userLoged.getAuthorities().iterator().next().getAuthority().equals("ADMIN"), "Comprobar que hay admin conectado");

		Administrator res = null;
		Assert.isTrue(admin.getName() != null && admin.getName() != "" && admin.getSurname() != null && admin.getSurname() != "" && admin.getUserAccount() != null && admin.getEmail() != null && admin.getEmail() != "", "Fallo en datos personales");

		final String regexEmail1 = "[^@]+@[^@]+\\.[a-zA-Z]{2,}";
		final Pattern patternEmail1 = Pattern.compile(regexEmail1);
		final Matcher matcherEmail1 = patternEmail1.matcher(admin.getEmail());

		final String regexEmail2 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@[A-z0-9]+\\.[A-z0-9.]+\\>";
		final Pattern patternEmail2 = Pattern.compile(regexEmail2);
		final Matcher matcherEmail2 = patternEmail2.matcher(admin.getEmail());

		final String regexEmail3 = "^[A-z0-9]+\\@$";
		final Pattern patternEmail3 = Pattern.compile(regexEmail3);
		final Matcher matcherEmail3 = patternEmail3.matcher(admin.getEmail());

		final String regexEmail4 = "^[A-z0-9]+\\s*[A-z0-9\\s]*\\s\\<[A-z0-9]+\\@\\>$";
		final Pattern patternEmail4 = Pattern.compile(regexEmail4);
		final Matcher matcherEmail4 = patternEmail4.matcher(admin.getEmail());

		Assert.isTrue((matcherEmail1.matches() == true || matcherEmail2.matches() == true || matcherEmail3.matches() == true || matcherEmail4.matches() == true), "Email");

		if (admin.getPhone() != "" || admin.getPhone() != null) {
			final String regexTelefono = "^\\+[1-9][0-9]{0,2}\\ \\([1-9][0-9]{0,2}\\)\\ [0-9]{4,}$|^\\+[1-9][0-9]{0,2}\\ [0-9]{4,}$|^[0-9]{4,}$";
			final Pattern patternTelefono = Pattern.compile(regexTelefono);
			final Matcher matcherTelefono = patternTelefono.matcher(admin.getPhone());
			Assert.isTrue(matcherTelefono.find() == true, "AdminService.save -> Telefono inv�lido");
		}

		//NUEVO
		Assert.isTrue(admin.getUserAccount().getUsername() != null && admin.getUserAccount().getUsername() != "", "Cuenta");
		Assert.isTrue(admin.getUserAccount().getPassword() != null && admin.getUserAccount().getPassword() != "", "Cuenta");

		if (admin.getId() == 0) {
			final Md5PasswordEncoder encoder;
			encoder = new Md5PasswordEncoder();
			final String hash = encoder.encodePassword(admin.getUserAccount().getPassword(), null);
			final UserAccount user = admin.getUserAccount();
			user.setPassword(hash);
		}

		Assert.isTrue(admin.getIsBanned() == 0 || admin.getIsBanned() == 1);

		res = this.adminRepo.save(admin);
		if (admin.getId() == 0)
			this.messageBoxService.createMessageBoxSystem(res);
		return res;
	}

}
