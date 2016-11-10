package mvc.service;

import mvc.dao.ToDoDao;
import mvc.model.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("toDoService")
@Transactional
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoDao dao;
	
	public ToDo findById(int id) {
		return dao.findById(id);
	}

	public void saveToDo(ToDo toDo) {
		dao.saveToDo(toDo);
	}

	public void updateToDo(ToDo toDo) {
		ToDo entity = dao.findById(toDo.getId());
		if(entity!=null){
			entity.setText(toDo.getText());
			entity.setDone(toDo.getDone());
			entity.setDeadLine(toDo.getDeadLine());
		}
	}

	public void deleteToDoById(int id) {
		dao.deleteToDoById(id);
	}

	public List<ToDo> findAllToDos() {
		return dao.findAllToDos();
	}

	public void doneToDoById(int id) {
		dao.doneToDoById(id);
	}

	public List<ToDo> findDoneToDos() {
		return dao.findDoneToDos();
	}

	public List<ToDo> findActualToDos() {
		return dao.findActualToDos();
	}

}
