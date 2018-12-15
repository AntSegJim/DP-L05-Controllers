
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import domain.Message;

@Controller
@RequestMapping("/message")
public class MessageController {

	@Autowired
	private MessageService	messageService;


	public MessageController() {
		super();
	}

	@RequestMapping(value = "/actor/send", method = RequestMethod.GET)
	public ModelAndView createMessage() {
		final ModelAndView result;
		final Message message;
		message = this.messageService.create();

		result = new ModelAndView("message/send");
		result.addObject("newMessage", message);
		return result;

	}

	@RequestMapping(value = "/actor/send", method = RequestMethod.POST)
	public ModelAndView sendMessage(@Valid final Message message, final BindingResult binding) {
		final ModelAndView result;
		if (!binding.hasErrors()) {
			this.messageService.sendMessage(message);
			result = new ModelAndView("redirect:messageBox/show.do");
		} else {
			result = new ModelAndView("message/send");
			result.addObject("newMessage", message);
		}

		return result;
	}

}
