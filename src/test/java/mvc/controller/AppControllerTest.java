package mvc.controller;

import mvc.model.ToDo;
import mvc.service.ToDoService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
//import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class AppControllerTest {

	@Mock
	ToDoService service;
	
	/*@Mock
	MessageSource message;*/
	
	@InjectMocks
	AppController appController;
	
	@Spy
	List<ToDo> toDos = new ArrayList<ToDo>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		toDos = getToDoList();
	}
	
	@Test
	public void listToDos(){
		when(service.findAllToDos()).thenReturn(toDos);
		Assert.assertEquals(appController.listToDos(model), "allToDos");
		Assert.assertEquals(model.get("toDos"), toDos);
		verify(service, atLeastOnce()).findAllToDos();
	}
	
	@Test
	public void newToDo(){
		Assert.assertEquals(appController.newToDo(model), "registration");
		Assert.assertNotNull(model.get("toDo"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((ToDo)model.get("toDo")).getId(), 0);
	}


	@Test
	public void saveToDoWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveToDo(any(ToDo.class));
		Assert.assertEquals(appController.saveToDo(toDos.get(0), result, model), "registration");
	}

	@Test
	public void saveToDoWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).saveToDo(any(ToDo.class));
		Assert.assertEquals(appController.saveToDo(toDos.get(0), result, model), "redirect:/list");
		//Assert.assertEquals(model.get("success"), "ToDo Axel registered successfully");
	}

	@Test
	public void editToDo(){
		ToDo emp = toDos.get(0);
		when(service.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(appController.editToDo(anyInt(), model), "registration");
		Assert.assertNotNull(model.get("toDo"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((ToDo)model.get("toDo")).getId(), 1);
	}

	@Test
	public void updateToDoWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateToDo(any(ToDo.class));
		Assert.assertEquals(appController.updateToDo(toDos.get(0), result, model,0), "registration");
	}

	@Test
	public void updateToDoWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		doNothing().when(service).updateToDo(any(ToDo.class));
		Assert.assertEquals(appController.updateToDo(toDos.get(0), result, model, 0), "redirect:/list");
		//Assert.assertEquals(model.get("success"), "ToDo Axel updated successfully");
	}
	
	
	@Test
	public void deleteToDo(){
		doNothing().when(service).deleteToDoById(anyInt());
		Assert.assertEquals(appController.deleteToDo(123), "redirect:/list");
	}

	public List<ToDo> getToDoList(){
		ToDo e1 = new ToDo();
		e1.setId(1);
		e1.setText("Axel");
		e1.setDeadLine(new Date());

		ToDo e2 = new ToDo();
		e2.setId(2);
		e2.setText("Jeremy");
		e2.setDeadLine(new Date());

		toDos.add(e1);
		toDos.add(e2);
		return toDos;
	}
}
