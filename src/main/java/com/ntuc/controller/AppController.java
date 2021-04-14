package com.ntuc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ntuc.model.Sdept;
import com.ntuc.model.Semps;
import com.ntuc.repository.DeptRepository;
import com.ntuc.service.SempService;

@Controller
public class AppController {
	@Autowired
	private SempService service;

	// insert dept respository
	@Autowired
	private DeptRepository catrepo;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		String keyword = null;
		return listByPage(model, 1, "name", "asc", keyword);
	}

	@GetMapping("/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir, @Param("keyword") String keyword) {
		Page<Semps> page = service.listAll(currentPage, sortField, sortDir, keyword);
		Long totalItems = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Semps> listProducts = page.getContent();

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);

		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortDir", reverseSortDir);
		return "index";
	}

	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Semps semp = new Semps();
		model.addAttribute("semp", semp);
		// insert dept
		List<Sdept> listDepts = catrepo.findAll();
		model.addAttribute("listDepts", listDepts);
		return "semp_form";

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(Semps semp, HttpServletRequest request) {
		String[] dIDs = request.getParameterValues("detailID");
		String[] dNames = request.getParameterValues("detailName");
		String[] dValues = request.getParameterValues("detailValue");

		for (int i = 0; i < dNames.length; i++) {
			if (dIDs != null && dIDs.length > 0)
				semp.setDetails(Integer.valueOf(dIDs[i]), dNames[i], dValues[i]);
			else {
				semp.addDetails(dNames[i], dValues[i]);
			}
		}
		service.save(semp);
		return "redirect:/";
	}

	// @PathVariable(name = "id")
	@RequestMapping("/edit/{id}")
	public String showEditProductForm(@PathVariable("id") Integer id, Model model) {
		// ModelAndView mav = new ModelAndView("semp_form");

		Semps semp = service.get(id);
		// insert new list
		model.addAttribute("semp", semp);
		List<Sdept> listDepts = catrepo.findAll();
		model.addAttribute("listDepts", listDepts);
//		mav.addObject("semp", semp);
		return "semp_form";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Integer id) {
		service.delete(id);

		return "redirect:/";
	}

	// Create Dept
	@RequestMapping("/depts")
	public String listCategories(Model model) {
		List<Sdept> listdepts = catrepo.findAll();
		model.addAttribute("listdepts", listdepts);
		return "sdepts";
	}

	@RequestMapping("/depts/new")
	public String ShowCategoryNewForm(Model model) {
		model.addAttribute("sdept", new Sdept());
		return "sdept_form";
	}

	@RequestMapping("/depts/save")
	public String saveCategory(Sdept sdept) {
		catrepo.save(sdept);
		return "redirect:/depts";
	}

}
