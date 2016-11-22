package mvc.dao;

import mvc.model.ToDo;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;


public class ToDoDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ToDoDao toDoDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("ToDo.xml"));
		return dataSet;
	}

	@Test
	public void findById(){
		Assert.assertNotNull(toDoDao.findById(1));
		Assert.assertNull(toDoDao.findById(3));
	}

	
	@Test
	public void saveToDo(){
		toDoDao.saveToDo(getSampleToDo());
		Assert.assertEquals(toDoDao.findAllToDos().size(), 3);
	}
	
	@Test
	public void deleteToDoBySsn(){
		toDoDao.deleteToDoById(1);
		Assert.assertEquals(toDoDao.findAllToDos().size(), 1);
	}
	
	@Test
	public void deleteToDoByInvalidId(){
		toDoDao.deleteToDoById(23423);
		Assert.assertEquals(toDoDao.findAllToDos().size(), 2);
	}

	@Test
	public void findAllToDos(){
		Assert.assertEquals(toDoDao.findAllToDos().size(), 2);
	}
	
	@Test
	public void findToDoById(){
		Assert.assertNotNull(toDoDao.findToDoById(1));
		Assert.assertNull(toDoDao.findToDoById(14545));
	}

	public ToDo getSampleToDo(){
		ToDo toDo = new ToDo();
		toDo.setText("Karen");
		toDo.setDeadLine(new Date());
		return toDo;
	}

}
