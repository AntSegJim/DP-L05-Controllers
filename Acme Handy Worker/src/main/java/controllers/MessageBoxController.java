
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ActorService;
import services.MessageBoxService;
import domain.Actor;
import domain.MessageBox;

@Controller
@RequestMapping("/messageBox")
public class MessageBoxController {

	@Autowired
	private MessageBoxService	messageBoxServive;

	@Autowired
	private ActorService		actorService;


	// Action-1 ---------------------------------------------------------------		

	//MUESTRA LAS CAJAS DE MENSAJE DE UN ACTOR
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView action1() {
		final ModelAndView result;
		final Collection<MessageBox> boxes;
		final UserAccount user = LoginService.getPrincipal();
		final Actor a = this.actorService.getActorByUserAccount(user.getId());

		boxes = this.messageBoxServive.findMessageBoxActor(a.getId());
		//
		result = new ModelAndView("messageBox/show");
		result.addObject("boxes", boxes);
		//
		return result;
	}

}
