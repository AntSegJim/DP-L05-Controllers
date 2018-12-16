
package controllers.handyWorker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import domain.FixUpTask;

@Controller
//@RequestMapping("/finder/handyWorker")
public class FinderHandyWorkerController2 {

	// Services ---------------------------------------------------------------

	@Autowired
	private FinderService		finderService;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private FixUpTaskService	fixUpTaskService;


	// Constructors -----------------------------------------------------------

	public FinderHandyWorkerController2() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public ModelAndView results() {
		ModelAndView result;
		//		final Integer id_user = LoginService.getPrincipal().getId();
		//		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		//final Finder finder = handyWorker.getFinder();
		final Collection<FixUpTask> fixUpTasks = this.fixUpTaskService.findAll();

		result = new ModelAndView("finder/results");

		result.addObject("fixUpTaks", fixUpTasks);

		return result;
	}

}
