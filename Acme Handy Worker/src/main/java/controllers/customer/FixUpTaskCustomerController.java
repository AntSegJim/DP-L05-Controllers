
package controllers.customer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CategoryService;
import services.FixUpTaskService;
import services.WarrantyService;
import controllers.AbstractController;
import domain.Category;
import domain.FixUpTask;
import domain.Warranty;

@Controller
@RequestMapping("/fix-up-task/customer")
public class FixUpTaskCustomerController extends AbstractController {

	@Autowired
	private FixUpTaskService	fixUpTaskService;
	@Autowired
	private CategoryService		categoryService;
	@Autowired
	private WarrantyService		warrantyService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<FixUpTask> fixUpTasks = this.fixUpTaskService.findAllCustomer();

		result = new ModelAndView("fixUpTask/list");
		result.addObject("fixUpTasks", fixUpTasks);
		result.addObject("requestURI", "fix-up-task/customer/list.do");

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;

		final FixUpTask fixUpTask = this.fixUpTaskService.create();
		result = new ModelAndView("fixUpTask/create");
		result.addObject("fixUpTask", fixUpTask);

		final Collection<Category> categories = this.categoryService.findAll();
		result.addObject("categories", categories);

		final Collection<Warranty> warranties = this.warrantyService.findAll();
		result.addObject("warranties", warranties);

		return result;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, params = "save")
	public ModelAndView actionSearch(@Valid final FixUpTask newf, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			this.fixUpTaskService.save(newf);
			result = new ModelAndView("redirect:list.do");
		} else {
			result = new ModelAndView("fixUpTask/create");
			result.addObject("fixUpTask", newf);
			final Collection<Category> categories = this.categoryService.findAll();
			result.addObject("categories", categories);

			final Collection<Warranty> warranties = this.warrantyService.findAll();
			result.addObject("warranties", warranties);
		}
		return result;
	}

}
