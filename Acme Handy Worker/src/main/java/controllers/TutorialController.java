
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.HandyWorkerService;
import services.PictureService;
import services.SectionService;
import services.SponsorshipService;
import services.TutorialService;
import domain.HandyWorker;
import domain.Picture;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial")
public class TutorialController extends AbstractController {

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private PictureService		pictureS;
	@Autowired
	private SectionService		sectionS;
	@Autowired
	private SponsorshipService	sponsorshipS;


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
		final Collection<Picture> pictures;
		final Collection<Section> sections;
		final Collection<Sponsorship> sponsorships;

		pictures = this.pictureS.finaAll();
		sections = this.sectionS.findAll();
		sponsorships = this.sponsorshipS.findAll();
		tutorial = this.tutorialService.create();

		result = new ModelAndView("tutorial/create");
		result.addObject("tutorial", tutorial);
		result.addObject("pictures", pictures);
		result.addObject("sections", sections);
		result.addObject("sponsorships", sponsorships);

		return result;
	}

	@RequestMapping(value = "/AllTutorials", method = RequestMethod.GET)
	public ModelAndView allTutorials() {
		final ModelAndView result;
		final Collection<Tutorial> tutorials;

		tutorials = this.tutorialService.findAll();

		result = new ModelAndView("tutorial/AllTutorials");
		result.addObject("tutorials", tutorials);

		return result;
	}

	@RequestMapping(value = "/showTutorialHW", method = RequestMethod.GET)
	public ModelAndView showTutorialHW(@RequestParam final int handyWorkerId) {
		final ModelAndView result;
		HandyWorker h;

		h = this.handyWorkerService.findOne(handyWorkerId);

		result = new ModelAndView("tutorial/showTutorialHW");
		result.addObject("h", h);

		return result;
	}
}
