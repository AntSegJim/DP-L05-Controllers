
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

import services.PictureService;
import services.TutorialService;
import domain.Picture;
import domain.Tutorial;

@Controller
@RequestMapping("/picture/handyWorker")
public class PictureHandyWorkerController extends AbstractController {

	@Autowired
	private TutorialService	tutorialService;
	@Autowired
	private PictureService	pictureService;


	@RequestMapping(value = "/showPictures", method = RequestMethod.GET)
	public ModelAndView showPictures(@RequestParam final int tutorialId) {
		final ModelAndView result;
		Tutorial t;
		Collection<Picture> pictures;

		t = this.tutorialService.findOne(tutorialId);
		pictures = t.getPicture();

		result = new ModelAndView("picture/showPictures");
		result.addObject("pictures", pictures);
		result.addObject("tutorial", t);

		return result;
	}

	@RequestMapping(value = "/createPicture", method = RequestMethod.GET)
	public ModelAndView createPicture() {
		ModelAndView result;
		Picture picture;

		picture = this.pictureService.create();
		result = new ModelAndView("picture/editPicture");
		result.addObject("picture", picture);
		return result;
	}

	@RequestMapping(value = "/editPicture", method = RequestMethod.GET)
	public ModelAndView editPicture(@RequestParam final int pictureId, @RequestParam final int tutorialId) {
		ModelAndView result;
		Picture picture;

		picture = this.pictureService.findOne(pictureId);
		Assert.notNull(picture);
		result = new ModelAndView("picture/editPicture");
		result.addObject("picture", picture);
		result.addObject("tutorialId", tutorialId);
		return result;
	}

	@RequestMapping(value = "/editPicture", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam(value = "tutorialId") final int tutorialId, @Valid final Picture picture, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = new ModelAndView("picture/editPicture");
		//result.addObject("picture", picture);
		else
			try {
				//final Collection<Picture> pictures = this.pictureService.finaAll();
				this.pictureService.save(picture);
				//pictures.add(nueva);
				result = new ModelAndView("redirect:showPictures.do?tutorialId=" + tutorialId);
			} catch (final Throwable oopd) {
				result = new ModelAndView("picture/editPicture");
			}
		return result;
	}
}
