package mvc.controller;

import mvc.model.ToDo;
import mvc.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	ToDoService service;
	
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listToDos(ModelMap model) {
		List<ToDo> toDos = service.findAllToDos();
		model.addAttribute("toDos", toDos);
		return "allToDos";
	}

	@RequestMapping(value = { "/list/done" }, method = RequestMethod.GET)
	public String listDoneToDos(ModelMap model) {
		List<ToDo> toDos = service.findDoneToDos();
		model.addAttribute("toDos", toDos);
		return "allToDos";
	}

	@RequestMapping(value = { "/list/actual" }, method = RequestMethod.GET)
	public String listActualToDos(ModelMap model) {
		List<ToDo> toDos = service.findActualToDos();
		model.addAttribute("toDos", toDos);
		return "allToDos";
	}

	@RequestMapping(value = { "/todo/new" }, method = RequestMethod.GET)
	public String newToDo(ModelMap model) {
		ToDo toDo = new ToDo();
		model.addAttribute("toDo", toDo);
		model.addAttribute("edit", false);
		return "registration";
	}

	@RequestMapping(value = { "/todo/new" }, method = RequestMethod.POST)
	public String saveToDo(@Valid ToDo toDo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "registration";
		}
		service.saveToDo(toDo);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/todo/edit/{id}" }, method = RequestMethod.GET)
	public String editToDo(@PathVariable int id, ModelMap model) {
		ToDo toDo = service.findById(id);
		model.addAttribute("toDo", toDo);
		model.addAttribute("edit", true);
		return "registration";
	}

	@RequestMapping(value = { "/todo/edit/{id}" }, method = RequestMethod.POST)
	public String updateToDo(@Valid ToDo toDo, BindingResult result, ModelMap model, @PathVariable int id) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			return "registration";
		}
		service.updateToDo(toDo);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/todo/delete/{id}" }, method = RequestMethod.GET)
	public String deleteToDo(@PathVariable int id) {
		service.deleteToDoById(id);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/todo/done/{id}" }, method = RequestMethod.GET)
	public String doneToDo(@PathVariable int id) {
		service.doneToDoById(id);
		return "redirect:/list";
	}
}
