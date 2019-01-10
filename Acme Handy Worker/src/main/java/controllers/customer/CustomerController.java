
package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import controllers.AbstractController;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	@Autowired
	private CustomerService	customerService;


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Customer customer;

		customer = this.customerService.create();

		result = new ModelAndView("customer/create");
		result.addObject("customer", customer);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Customer customer, final BindingResult binding) {
		ModelAndView result;
		try {
			if (!binding.hasErrors()) {
				this.customerService.save(customer);
				result = new ModelAndView("redirect:action-2.do");
			} else {
				result = new ModelAndView("customer/create");
				result.addObject("customer", customer);
			}
		} catch (final Exception e) {
			result = new ModelAndView("customer/create");
			result.addObject("exception", e);
			customer.getUserAccount().setPassword("");
			result.addObject("customer", customer);
		}

		return result;
	}

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("customer/action-2");

		return result;
	}

}
