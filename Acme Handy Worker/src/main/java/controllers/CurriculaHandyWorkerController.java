
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CurriculaService;
import services.HandyWorkerService;
import domain.Curricula;
import domain.HandyWorker;

@Controller
@RequestMapping("/curricula/handyWorker")
public class CurriculaHandyWorkerController extends AbstractController {

	@Autowired
	private CurriculaService	curriculaService;
	@Autowired
	private HandyWorkerService	hwService;


	public CurriculaHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/curriculas", method = RequestMethod.GET)
	public ModelAndView curriculas() {
		ModelAndView result;
		final Collection<Curricula> curriculas;
		final UserAccount user = LoginService.getPrincipal();
		final HandyWorker h = this.hwService.handyWorkerUserAccount(user.getId());
		curriculas = this.curriculaService.findAllHandyWorkerCurricula(h.getId());

		result = new ModelAndView("curricula/curriculas");
		result.addObject("curriculas", curriculas);
		//result.addObject("requestURI", "complaint/customer/complaints.do");

		return result;
	}

}
