
package controllers.handyWorker;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.CategoryService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.Finder;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder/handy-worker")
public class FinderHandyWorkerController extends AbstractController {

	@Autowired
	private HandyWorkerService	handyWorkerService;
	@Autowired
	private FinderService		finderService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private WarrantyService		warrantyService;
	@Autowired
	private FixUpTaskService	fixUpTaskService;


	// Constructors -----------------------------------------------------------

	public FinderHandyWorkerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------		

	//MUESTRA EL FILTRO DEL FINDER
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show() {
		final ModelAndView result;
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();

		result = new ModelAndView("finder/show");
		result.addObject("finder", finder);

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView actionSave(@Valid final Finder newFinder, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
		final ModelAndView result;

		newFinder.setCategory(this.categoryService.findOne(categoryId));
		newFinder.setWarranty(this.warrantyService.findOne(warrantyId));

		this.finderService.save(newFinder);

		result = new ModelAndView("finder/show");
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		result.addObject("finder", finder);

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "search")
	public ModelAndView actionSearch(@Valid final Finder newFinder, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
		final ModelAndView result;

		String categoryName = "";
		if (categoryId != 0) {
			newFinder.setCategory(this.categoryService.findOne(categoryId));
			categoryName = newFinder.getCategory().getName();
		}
		String warrantyTitle = "";
		if (warrantyId != 0) {
			newFinder.setWarranty(this.warrantyService.findOne(warrantyId));
			warrantyTitle = newFinder.getWarranty().getTitle();
		}

		final Finder sf = this.finderService.save(newFinder);

		result = new ModelAndView("finder/results");
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		finder.setFixUpTask(this.fixUpTaskService.filterFixUpTask(sf.getTicker(), sf.getDescription(), sf.getAddress(), sf.getStartDate(), sf.getEndDate(), sf.getLowPrice(), sf.getHighPrice(), categoryName, warrantyTitle));

		result.addObject("requestURI", "finder/handy-worker/results.do");
		result.addObject("fixUpTasks", finder.getFixUpTask());

		return result;
	}

	@RequestMapping(value = "/list-results", method = RequestMethod.GET)
	public ModelAndView listFixUpTaskOfTheResults() {
		final ModelAndView result;
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();

		result = new ModelAndView("finder/results");

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}
}
