package mvc.service;

import mvc.model.ToDo;
import java.util.List;

public interface ToDoService {

	ToDo findById(int id);
	
	void saveToDo(ToDo toDo);
	
	void updateToDo(ToDo toDo);
	
	void deleteToDoById(int id);

	List<ToDo> findAllToDos();

	void doneToDoById(int id);

    List<ToDo> findDoneToDos();

	List<ToDo> findActualToDos();
}
