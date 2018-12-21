/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import domain.Actor;

@Controller
@RequestMapping("/profile")
public class ProfileController extends AbstractController {

	@Autowired
	private ActorService	actorService;


	// Action-2 ---------------------------------------------------------------		

	//VER SUS DATOS PERSONALES
	@RequestMapping(value = "/action-2", method = RequestMethod.GET)
	public ModelAndView action2() {
		ModelAndView result;
		Actor a;

		final UserAccount user = LoginService.getPrincipal();
		a = this.actorService.getActorByUserAccount(user.getId());

		result = new ModelAndView("profile/action-2");
		result.addObject("actor", a);

		return result;
	}

	// Action-3 ---------------------------------------------------------------		

	@RequestMapping(value = "/action-3", method = RequestMethod.GET)
	public ModelAndView action3() {
		//throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
		ModelAndView result;
		Actor a;

		final UserAccount user = LoginService.getPrincipal();
		a = this.actorService.getActorByUserAccount(user.getId());

		result = new ModelAndView("profile/action-3");
		result.addObject("actor", a);

		return result;

	}

	@RequestMapping(value = "/action-3", method = RequestMethod.POST)
	public ModelAndView action3(@Valid final Actor actor, final BindingResult binding) {
		//throw new RuntimeException("Oops! An *expected* exception was thrown. This is normal behaviour.");
		ModelAndView result;
		if (binding.hasErrors()) {
			this.actorService.save(actor);
			result = new ModelAndView("redirect:action-2.do");
		} else {
			result = new ModelAndView("profile/action-3");
			result.addObject("actor", actor);

		}
		return result;

	}

}
