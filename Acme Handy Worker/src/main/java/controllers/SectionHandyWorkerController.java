
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import domain.Section;
import domain.Tutorial;

@Controller
@RequestMapping("/section")
public class SectionHandyWorkerController extends AbstractController {

	@Autowired
	private TutorialService	tutorialService;


	@RequestMapping(value = "/handyWorker/showSections", method = RequestMethod.GET)
	public ModelAndView showSections(@RequestParam final int tutorialId) {
		final ModelAndView result;
		Tutorial t;
		Collection<Section> sections;

		t = this.tutorialService.findOne(tutorialId);
		sections = t.getSection();

		result = new ModelAndView("section/showSections");
		result.addObject("sections", sections);

		return result;
	}
}
