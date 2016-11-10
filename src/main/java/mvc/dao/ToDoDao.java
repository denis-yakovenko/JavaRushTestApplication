package mvc.dao;

import mvc.model.ToDo;
import java.util.List;

public interface ToDoDao {

	ToDo findById(int id);

	void saveToDo(ToDo toDo);
	
	void deleteToDoById(int id);
	
	List<ToDo> findAllToDos();

	ToDo findToDoById(int id);

	void doneToDoById(int id);

    List<ToDo> findDoneToDos();

	List<ToDo> findActualToDos();

}
