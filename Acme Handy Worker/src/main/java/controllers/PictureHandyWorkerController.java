
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import domain.Picture;
import domain.Tutorial;

@Controller
@RequestMapping("/picture")
public class PictureHandyWorkerController extends AbstractController {

	@Autowired
	private TutorialService	tutorialService;


	@RequestMapping(value = "/handyWorker/showPictures", method = RequestMethod.GET)
	public ModelAndView showPictures(@RequestParam final int tutorialId) {
		final ModelAndView result;
		Tutorial t;
		Collection<Picture> pictures;

		t = this.tutorialService.findOne(tutorialId);
		pictures = t.getPicture();

		result = new ModelAndView("picture/showPictures");
		result.addObject("pictures", pictures);

		return result;
	}
}
