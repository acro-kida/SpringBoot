package com.example.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.entity.Book;
import com.example.springboot.repository.DataRepository;

@Controller
public class MyController {
	//テンプレート
	private String templateIndex ="index";
	private String templeteEdit ="edit";

	//テンプレート内変数式
	private String templateVariableData = "data";
	private String templateVariablFormModel = "formModel";

	@Autowired
	DataRepository repository;

	@RequestMapping(value = "/" , method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName(templateIndex);
		Iterable<Book> list = repository.findAll();
		mav.addObject(templateVariableData , list);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}" , method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute Book book , @PathVariable int id , ModelAndView mav) {
		mav.setViewName(templeteEdit);
		Optional<Book> bookData = repository.findById((long)id);
		mav.addObject(templateVariablFormModel, bookData.get());
		return mav;
	}
	@RequestMapping(value = "/edit" , method = RequestMethod.POST)
	@Transactional(readOnly = false)
	public ModelAndView update(@ModelAttribute Book book , ModelAndView mav) {
		repository.saveAndFlush(book);
		return new ModelAndView("redirect:/");
	}

}
