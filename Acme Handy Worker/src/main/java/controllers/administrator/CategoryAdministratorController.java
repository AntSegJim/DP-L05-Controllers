
package controllers.administrator;

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

import services.CategoryService;
import domain.Category;

@Controller
@RequestMapping("/category/administrator")
public class CategoryAdministratorController {

	@Autowired
	private CategoryService	categoryService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Category> categories;

		categories = this.categoryService.findAll();

		result = new ModelAndView("category/list");
		result.addObject("categories", categories);
		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int categoryId) {
		ModelAndView result;
		Category category;
		final Collection<Category> categories;

		category = this.categoryService.findOne(categoryId);
		Assert.notNull(category);

		categories = this.categoryService.findAll();
		categories.remove(category);

		result = new ModelAndView("category/edit");
		result.addObject("category", category);
		result.addObject("categories", categories);

		return result;
	}

	//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	//	public ModelAndView save(@Valid final Category category, final BindingResult binding, @RequestParam("parent") final int categoryId) {
	//		ModelAndView result;
	//
	//		if (!binding.hasErrors()) {
	//			final Category c = this.categoryService.findOne(categoryId);
	//			category.setParent(c);
	//			this.categoryService.save(category);
	//			result = new ModelAndView("redirect:list.do");
	//		} else {
	//			final Collection<Category> categories = this.categoryService.findAll();
	//			categories.remove(category);
	//			result = new ModelAndView("category/edit");
	//			result.addObject("category", category);
	//			result.addObject("categories", categories);
	//		}
	//		return result;
	//
	//	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Category category, final BindingResult binding, @RequestParam("parent") final int categoryId) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			final Category c = this.categoryService.findOne(categoryId);
			category.setParent(c);
			this.categoryService.save(category);
			result = new ModelAndView("category/list");
			final Collection<Category> categories = this.categoryService.findAll();
			result.addObject("categories", categories);

		} else
			result = new ModelAndView("redirect:edit.do?categoryId=" + category.getId());
		return result;

	}

}
