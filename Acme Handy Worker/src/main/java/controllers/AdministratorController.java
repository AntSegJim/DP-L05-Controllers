/*
 * AdministratorController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.FixUpTaskService;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	//DASHBOARD
	@Autowired
	private FixUpTaskService		fixUpTaskService;


	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView action1() {
		ModelAndView result;
		final Administrator administrator;

		administrator = this.administratorService.create();

		result = new ModelAndView("administrator/create");
		result.addObject("administrator", administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Administrator administrator, final BindingResult binding) {
		ModelAndView result;
		try {
			if (!binding.hasErrors()) {
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:action-2.do");
			} else {
				result = new ModelAndView("administrator/create");
				result.addObject("administrator", administrator);
			}
		} catch (final Exception e) {
			result = new ModelAndView("administrator/create");
			result.addObject("exception", e);
			result.addObject("administrator", administrator);
		}

		return result;
	}

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		ModelAndView result;

		final List<Object[]> fixUpTask2 = this.fixUpTaskService.maxMinAvgDevFixUpTask();
		final Double a2 = (Double) fixUpTask2.get(0)[0];
		final Double b2 = (Double) fixUpTask2.get(0)[1];
		final Double c2 = (Double) fixUpTask2.get(0)[2];
		final Double d2 = (Double) fixUpTask2.get(0)[3];

		final List<Object[]> fixUpTask = this.fixUpTaskService.maxMinAvgDevFixUpTaskApp();
		final Integer a = (Integer) fixUpTask.get(0)[0];
		final Integer b = (Integer) fixUpTask.get(0)[1];
		final Double c = (Double) fixUpTask.get(0)[2];
		final Double d = (Double) fixUpTask.get(0)[3];

		result = new ModelAndView("administrator/dashboard");
		result.addObject("fixUp1", a);
		result.addObject("fixUp2", b);
		result.addObject("fixUp3", c);
		result.addObject("fixUp4", d);

		result.addObject("fixUp5", a2);
		result.addObject("fixUp6", b2);
		result.addObject("fixUp7", c2);
		result.addObject("fixUp8", d2);

		return result;
	}
}
