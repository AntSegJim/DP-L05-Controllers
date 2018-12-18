
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CreditCardTypeService;
import domain.CreditCardType;

@Controller
@RequestMapping("/creditCardType/administrator")
public class CreditCardTypeAdministratorController {

	@Autowired
	private CreditCardTypeService	creditCardTypeService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<CreditCardType> creditCardTypes;

		creditCardTypes = this.creditCardTypeService.findAll();
		final CreditCardType newCreditCardType = this.creditCardTypeService.create();
		result = new ModelAndView("creditCardType/list");
		result.addObject("requestURI", "creditCardType/administrator/list.do");
		result.addObject("creditCardTypes", creditCardTypes);
		result.addObject("creditCardType", newCreditCardType);
		return result;

	}

	@RequestMapping(value = "/list", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final CreditCardType creditCardType, final BindingResult binding) {
		ModelAndView result;

		if (!binding.hasErrors()) {
			this.creditCardTypeService.save(creditCardType);
			result = new ModelAndView("redirect:list.do");
		} else {
			final Collection<CreditCardType> ccts = this.creditCardTypeService.findAll();
			result = new ModelAndView("category/edit");
			result.addObject("category", creditCardType);
			result.addObject("creditCardTypes", ccts);
		}
		return result;
	}
}
