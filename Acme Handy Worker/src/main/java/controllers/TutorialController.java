
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.HandyWorkerService;
import services.TutorialService;
import domain.HandyWorker;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial")
public class TutorialController {

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@RequestMapping(value = "/handyWorker/show", method = RequestMethod.GET)
	public ModelAndView action1() {
		final ModelAndView result;
		final Collection<Tutorial> tutorials;
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker h = this.handyWorkerService.handyWorkerUserAccount(user.getId());

		tutorials = this.tutorialService.getTutorialsByHandyWorker(h.getId());

		result = new ModelAndView("tutorial/show");
		result.addObject("tutorials", tutorials);

		return result;
	}

	@RequestMapping(value = "/handyWorker/create", method = RequestMethod.GET)
	public ModelAndView action2() {
		final ModelAndView result;
		final Tutorial tutorial;

		tutorial = this.tutorialService.create();

		result = new ModelAndView("tutorial/create");
		result.addObject("tutorial", tutorial);

		return result;
	}

}
