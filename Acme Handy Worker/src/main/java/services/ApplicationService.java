
package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.CreditCard;
import domain.Customer;
import domain.FixUpTask;
import domain.HandyWorker;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	private ApplicationRepository	applicationRepository;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private CreditCardService		CCService;
	@Autowired
	private FixUpTaskService		FUTService;
	@Autowired
	private HandyWorkerService		HWService;


	public Application create() {
		final Application a = new Application();
		final CreditCard cc = this.CCService.create();
		final FixUpTask fut = this.FUTService.create();
		final HandyWorker hw = this.HWService.create();
		a.setComments(new HashSet<String>());
		a.setCreditCard(cc);
		a.setFixUpTask(fut);
		a.setHandyWorker(hw);
		a.setMoment(new Date());
		a.setPrice(0.);
		a.setStatus(0);
		return a;
	}
	public Collection<Application> findAll() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		return this.applicationRepository.findAllCustomerApplication(c.getId());
	}

	public Application save(final Application a) {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		final Customer c = this.customerService.customerByUserAccount(userAccount.getId());
		Assert.isTrue(Integer.valueOf(a.getFixUpTask().getCustomer().getId()).equals(c.getId()));
		Assert.isTrue(a.getMoment() != null && a.getMoment().before(Calendar.getInstance().getTime()) && a.getStatus() >= 0 && a.getStatus() <= 2 && a.getHandyWorker() != null && a.getFixUpTask() != null);
		if (a.getStatus() == 1)
			Assert.isTrue(a.getCreditCard() != null);
		else
			Assert.isTrue(a.getCreditCard().equals(null));
		return this.applicationRepository.save(a);
	}
}
