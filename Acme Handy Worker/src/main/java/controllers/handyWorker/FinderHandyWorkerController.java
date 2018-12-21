
package controllers.handyWorker;

import java.util.Collection;
import java.util.Date;

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
import domain.FixUpTask;
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

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "search")
	public ModelAndView actionSearch(@Valid final Finder newf, final BindingResult binding, @RequestParam("category") final Integer categoryId, @RequestParam("warranty") final Integer warrantyId) {
		final ModelAndView result;

		String categoryName = "";
		if (categoryId != 0) {
			newf.setCategory(this.categoryService.findOne(categoryId));
			categoryName = newf.getCategory().getName();
		}
		String warrantyTitle = "";
		if (warrantyId != 0) {
			newf.setWarranty(this.warrantyService.findOne(warrantyId));
			warrantyTitle = newf.getWarranty().getTitle();
		}
		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();
		final Collection<FixUpTask> resultado = this.fixUpTaskService.filterFixUpTask(newf.getTicker(), newf.getDescription(), newf.getAddress(), newf.getStartDate(), newf.getEndDate(), newf.getLowPrice(), newf.getHighPrice(), categoryName, warrantyTitle);
		finder.setTicker(newf.getTicker());
		finder.setAddress(newf.getAddress());
		finder.setDescription(newf.getDescription());
		finder.setStartDate(newf.getStartDate());
		finder.setEndDate(newf.getEndDate());
		finder.setLowPrice(newf.getLowPrice());
		finder.setHighPrice(newf.getHighPrice());
		finder.setCategory(newf.getCategory());
		finder.setWarranty(newf.getWarranty());
		finder.setMoment(new Date());
		finder.setFixUpTask(resultado);
		this.finderService.save(finder);

		result = new ModelAndView("redirect:list.do");

		return result;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listFixUpTaskOfTheResults() {
		final ModelAndView result;

		final Integer id_user = LoginService.getPrincipal().getId();
		final HandyWorker handyWorker = this.handyWorkerService.handyWorkerUserAccount(id_user);
		final Finder finder = handyWorker.getFinder();

		result = new ModelAndView("finder/results");
		result.addObject("requestURI", "finder/handy-worker/list.do");
		result.addObject("fixUpTasks", finder.getFixUpTask());
		return result;
	}
}
