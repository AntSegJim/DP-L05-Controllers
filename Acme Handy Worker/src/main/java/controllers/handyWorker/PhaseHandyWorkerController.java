/*
 * AnnouncementCustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.handyWorker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.PhaseService;
import controllers.AbstractController;
import domain.Application;
import domain.Phase;

@Controller
@RequestMapping("/phase/handyWorker")
public class PhaseHandyWorkerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private PhaseService		phaseService;

	@Autowired
	private ApplicationService	applicationService;


	// Constructors -----------------------------------------------------------

	public PhaseHandyWorkerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listIdComplaint(@RequestParam final int applicationId) {
		final ModelAndView result;

		Collection<Phase> phases;
		final Application application = this.applicationService.findOne(applicationId);

		phases = this.phaseService.findAllApplicationId(applicationId);

		result = new ModelAndView("phase/list");
		result.addObject("requestURI", "phase/handyWorker/list.do");
		result.addObject("phases", phases);
		result.addObject("application", application);

		return result;
	}
}
// Creation ---------------------------------------------------------------

//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public ModelAndView create(@Valid final int complaintId) {
//		ModelAndView result;
//		Report report;
//
//		report = this.reportService.create();
//		report.setComplaint(this.complaintService.findOne(complaintId));
//		result = this.createEditModelAndView(report);
//
//		return result;
//	}
//	// Edition ----------------------------------------------------------------
//
//	@RequestMapping(value = "/edit", method = RequestMethod.GET)
//	public ModelAndView edit(@RequestParam final int reportId) {
//		ModelAndView result;
//		Report report;
//
//		report = this.reportService.findOne(reportId);
//		Assert.notNull(report);
//
//		result = this.createEditModelAndView(report);
//
//		return result;
//	}
//
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
//	public ModelAndView save(@Valid final Report report, final BindingResult binding) {
//		ModelAndView result;
//
//		if (!binding.hasErrors()) {
//			this.reportService.save(report);
//			final Integer idComplaint = report.getComplaint().getId();
//			result = new ModelAndView("redirect:list.do?complaintId=" + idComplaint);
//		} else
//			result = this.createEditModelAndView(report);
//
//		return result;
//	}
//	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
//	public ModelAndView delete(final Report report, final BindingResult binding) {
//		ModelAndView result;
//
//		try {
//			this.reportService.delete(report);
//			final Integer idComplaint = report.getComplaint().getId();
//			result = new ModelAndView("redirect:list.do?complaintId=" + idComplaint);
//		} catch (final Throwable oops) {
//			result = this.createEditModelAndView(report, "report.commit.error");
//		}
//
//		return result;
//	}
//
//	// Ancillary methods ------------------------------------------------------
//
//	protected ModelAndView createEditModelAndView(final Report report) {
//		ModelAndView result;
//
//		result = this.createEditModelAndView(report, null);
//
//		return result;
//	}
//
//	protected ModelAndView createEditModelAndView(final Report report, final String message) {
//		ModelAndView result;
//		final Collection<Attachment> attachments;
//		final Complaint complaint;
//
//		complaint = report.getComplaint();
//
//		attachments = this.attachmentService.findAll();
//
//		result = new ModelAndView("report/edit");
//		result.addObject("report", report);
//		result.addObject("attachments", attachments);
//		result.addObject("complaint", complaint);
//		result.addObject("message", message);
//
//		return result;
//	}
//
//}
