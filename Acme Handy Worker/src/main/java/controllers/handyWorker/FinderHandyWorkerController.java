
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
import services.FilterService;
import services.FinderService;
import services.FixUpTaskService;
import services.HandyWorkerService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.Filter;
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
	private FilterService		filterService;
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
	public ModelAndView action1() {
		final ModelAndView result;
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		final Filter filter = finder.getFilter();

		result = new ModelAndView("finder/show");
		result.addObject("filter", filter);

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView actionSave(@Valid final Filter newFilter, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
		final ModelAndView result;

		newFilter.setCategory(this.categoryService.findOne(categoryId));
		newFilter.setWarranty(this.warrantyService.findOne(warrantyId));

		this.filterService.save(newFilter);

		result = new ModelAndView("finder/show");
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		final Filter filter = finder.getFilter();
		result.addObject("filter", filter);

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "search")
	public ModelAndView actionSearch(@Valid final Filter newFilter, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
		final ModelAndView result;

		String categoryName = "";
		if (categoryId != 0) {
			newFilter.setCategory(this.categoryService.findOne(categoryId));
			categoryName = newFilter.getCategory().getName();
		}
		String warrantyTitle = "";
		if (warrantyId != 0) {
			newFilter.setWarranty(this.warrantyService.findOne(warrantyId));
			warrantyTitle = newFilter.getWarranty().getTitle();
		}

		final Filter sf = this.filterService.save(newFilter);

		result = new ModelAndView("finder/results");
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		finder.setFixUpTask(this.fixUpTaskService.filterFixUpTask(sf.getTicker(), sf.getDescription(), sf.getAddress(), sf.getStartDate(), sf.getEndDate(), sf.getLowPrice(), sf.getHighPrice(), categoryName, warrantyTitle));

		result.addObject("requestURI", "finder/handy-worker/results.do");
		result.addObject("fixUpTasks", finder.getFixUpTask());

		return result;
	}

	//	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "search")
	//	public ModelAndView actionSearch(@Valid final Filter newFilter, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
	//		final ModelAndView result;
	//
	//		newFilter.setCategory(this.categoryService.findOne(categoryId));
	//		newFilter.setWarranty(this.warrantyService.findOne(warrantyId));
	//
	//		final Filter sf = this.filterService.save(newFilter);
	//
	//		result = new ModelAndView("finder/results");
	//		final Integer id_user = LoginService.getPrincipal().getId();
	//		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
	//		final Finder finder = handyWorker.getFinder();
	//		finder.setFixUpTask(this.fixUpTaskService.filterFixUpTask(sf.getTicker(), sf.getDescription(), sf.getAddress(), sf.getStartDate(), sf.getEndDate(), sf.getLowPrice(), sf.getHighPrice(), sf.getCategory().getName(), sf.getWarranty().getTitle()));
	//
	//		result.addObject("requestURI", "finder/handy-worker/results.do");
	//		result.addObject("fixUpTasks", finder.getFixUpTask());
	//
	//		return result;
	//	}
}
