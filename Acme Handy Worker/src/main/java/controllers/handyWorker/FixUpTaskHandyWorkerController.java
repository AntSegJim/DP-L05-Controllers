
package controllers.handyWorker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.FixUpTaskService;
import controllers.AbstractController;
import domain.Customer;
import domain.FixUpTask;

@Controller
@RequestMapping("/fix-up-task/handy-worker")
public class FixUpTaskHandyWorkerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	CustomerService				customerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<FixUpTask> fixUpTasks = this.fixUpTaskService.findAll();

		result = new ModelAndView("fixUpTask/list");
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "fix-up-task/handy-worker/list.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int fixUpTaskId) {
		ModelAndView result;
		FixUpTask fixUpTask;
		try {
			fixUpTask = this.fixUpTaskService.findOne(fixUpTaskId);
			Assert.notNull(fixUpTask);
			result = new ModelAndView("fixUpTask/show");
			result.addObject("fixUpTask", fixUpTask);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/customer-data", method = RequestMethod.GET)
	public ModelAndView info(@RequestParam final int customerId) {
		ModelAndView result;
		Customer customer;
		try {
			customer = this.customerService.findOne(customerId);
			Assert.notNull(customer);
			final Collection<FixUpTask> f = this.fixUpTaskService.findAllCustomerById(customerId);
			result = new ModelAndView("fixUpTask/customerData");
			result.addObject("customer", customer);
			result.addObject("fixUpTasks", f);
			result.addObject("requestURI", "fix-up-task/handy-worker/customer-data.do");
		} catch (final Exception e) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

}
