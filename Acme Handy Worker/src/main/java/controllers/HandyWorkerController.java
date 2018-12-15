
package controllers;

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
import domain.Category;
import domain.Filter;
import domain.Finder;
import domain.FixUpTask;
import domain.HandyWorker;
import domain.Warranty;

@Controller
@RequestMapping("/finder")
public class HandyWorkerController {

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

	@RequestMapping(value = "/handy-worker/save", method = RequestMethod.POST)
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
	//MUESTRA EL FILTRO DEL FINDER
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public ModelAndView results() {
		final ModelAndView result;

		final Collection<FixUpTask> fixs = this.fixUpTaskService.findAll();

		result = new ModelAndView("finder/results");
		result.addObject("fixUpTasks", fixs);

		return result;
	}

}
