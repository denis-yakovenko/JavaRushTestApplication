package mvc.service;

import mvc.dao.ToDoDao;
import mvc.model.ToDo;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ToDoServiceImplTest {

	@Mock
	ToDoDao dao;
	
	@InjectMocks
	ToDoServiceImpl toDoService;
	
	@Spy
	List<ToDo> toDos = new ArrayList<ToDo>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		toDos = getToDoList();
	}

	@Test
	public void findById(){
		ToDo emp = toDos.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(toDoService.findById(emp.getId()),emp);
	}

	@Test
	public void saveToDo(){
		doNothing().when(dao).saveToDo(any(ToDo.class));
		toDoService.saveToDo(any(ToDo.class));
		verify(dao, atLeastOnce()).saveToDo(any(ToDo.class));
	}
	
	@Test
	public void updateToDo(){
		ToDo emp = toDos.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		toDoService.updateToDo(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteToDoBySsn(){
		doNothing().when(dao).deleteToDoById(anyInt());
		toDoService.deleteToDoById(anyInt());
		verify(dao, atLeastOnce()).deleteToDoById(anyInt());
	}
	
	@Test
	public void findAllToDos(){
		when(dao.findAllToDos()).thenReturn(toDos);
		Assert.assertEquals(toDoService.findAllToDos(), toDos);
	}
	
	@Test
	public void findToDoBySsn(){
		ToDo emp = toDos.get(0);
		when(dao.findToDoById(anyInt())).thenReturn(emp);
		Assert.assertEquals(toDoService.findById(anyInt()), emp);
	}

	@Test
	public void isToDoSsnUnique(){
		ToDo emp = toDos.get(0);
		when(dao.findToDoById(anyInt())).thenReturn(emp);
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
