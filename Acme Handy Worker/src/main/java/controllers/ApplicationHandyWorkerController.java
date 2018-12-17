
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ApplicationService;
import services.CreditCardService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import domain.Application;
import domain.CreditCard;
import domain.FixUpTask;
import domain.HandyWorker;

@Controller
@RequestMapping("/application/handyWorker")
public class ApplicationHandyWorkerController extends AbstractController {

	public ApplicationHandyWorkerController() {
		super();
	}


	@Autowired
	private HandyWorkerService	HWService;
	@Autowired
	private ApplicationService	applicationS;
	@Autowired
	private CreditCardService	creditCardS;
	@Autowired
	private FixUpTaskService	fixUpTaskS;


	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public ModelAndView applications() {
		ModelAndView result;
		final Collection<Application> applications;
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker h = this.HWService.handyWorkerUserAccount(user.getId());
		applications = h.getApplication();

		result = new ModelAndView("application/applications");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/handyWorker/applications.do");

		return result;
	}

	@RequestMapping(value = "/createApplication", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Application application;
		final Collection<CreditCard> creditCards;
		final Collection<FixUpTask> fixUpTasks;

		creditCards = this.creditCardS.findAll();
		fixUpTasks = this.fixUpTaskS.findAll();

		application = this.applicationS.create();
		result = new ModelAndView("application/editApplication");
		result.addObject("application", application);
		result.addObject("creditCards", creditCards);
		result.addObject("fixUpTasks", fixUpTasks);
		return result;
	}
	@RequestMapping(value = "/editApplication", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		//CreditCard creditCardAppliEdit;
		//FixUpTask fixUpTaskAppliEdit;
		final Collection<CreditCard> creditCards;
		final Collection<FixUpTask> fixUpTasks;

		creditCards = this.creditCardS.findAll();
		fixUpTasks = this.fixUpTaskS.findAll();

		application = this.applicationS.findOne(applicationId);
		//creditCardAppliEdit = application.getCreditCard();
		//fixUpTaskAppliEdit = application.getFixUpTask();
		Assert.notNull(application);

		result = new ModelAndView("application/editApplication");
		result.addObject("application", application);
		//result.addObject("fixUpTask", fixUpTaskAppliEdit);
		//result.addObject("creditCard", creditCardAppliEdit);
		result.addObject("creditCards", creditCards);
		result.addObject("fixUpTasks", fixUpTasks);

		return result;
	}

	@RequestMapping(value = "/editApplication", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Application application, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = new ModelAndView("application/editApplication");
		else
			try {
				this.applicationS.save(application);
				result = new ModelAndView("redirect:applications.do");
			} catch (final Throwable oops) {
				result = new ModelAndView("application/editApplication");
			}
		return result;
	}

}
