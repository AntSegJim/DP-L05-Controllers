
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ComplaintService;
import services.CustomerService;
import services.FixUpTaskService;
import services.RefereeService;
import domain.Complaint;
import domain.Customer;
import domain.FixUpTask;
import domain.Referee;

@Controller
@RequestMapping("/complaint/customer")
public class ComplaintCustomerController {

	@Autowired
	private CustomerService		CService;
	@Autowired
	private ComplaintService	complaintS;
	@Autowired
	private RefereeService		refereeS;
	@Autowired
	private FixUpTaskService	fixUpTaskS;


	public ComplaintCustomerController() {
		super();
	}

	@RequestMapping(value = "/complaints", method = RequestMethod.GET)
	public ModelAndView complaints() {
		ModelAndView result;
		final Collection<Complaint> complaints;
		final UserAccount user = LoginService.getPrincipal();
		final Customer c = this.CService.customerByUserAccount(user.getId());
		complaints = this.complaintS.findAllCustomerComplaint(c.getId());

		result = new ModelAndView("complaint/complaints");
		result.addObject("complaints", complaints);
		result.addObject("requestURI", "complaint/customer/complaints.do");

		return result;
	}

	@RequestMapping(value = "/createComplaint", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Complaint complaint;
		final Collection<Referee> referees;
		final Collection<FixUpTask> fixUpTasks;

		referees = this.refereeS.findAll();
		fixUpTasks = this.fixUpTaskS.findAll();

		complaint = this.complaintS.create();
		result = new ModelAndView("complaint/editComplaint");
		result.addObject("complaint", complaint);
		result.addObject("referees", referees);
		result.addObject("fixUpTasks", fixUpTasks);
		return result;
	}
	@RequestMapping(value = "/editComplaint", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int complaintId) {
		ModelAndView result;
		Complaint complaint;
		final Collection<Referee> referees;
		final Collection<FixUpTask> fixUpTasks;

		referees = this.refereeS.findAll();
		fixUpTasks = this.fixUpTaskS.findAll();

		complaint = this.complaintS.findOne(complaintId);
		Assert.notNull(complaint);

		result = new ModelAndView("complaint/editComplaint");
		result.addObject("complaint", complaint);
		result.addObject("referees", referees);
		result.addObject("fixUpTasks", fixUpTasks);
		return result;
	}
}
