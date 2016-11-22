package mvc.dao;

import mvc.model.ToDo;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("toDoDao")
public class ToDoDaoImpl extends AbstractDao<Integer, ToDo> implements ToDoDao {

	public ToDo findById(int id) {
		return getByKey(id);
	}

	public void saveToDo(ToDo toDo) {
		persist(toDo);
	}

	public void deleteToDoById(int id) {
		Query query = getSession().createSQLQuery("delete from ToDo where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
/*
		ToDo toDo = getSession().get(ToDo.class,id);
		getSession().delete(toDo);
*/
	}

	@SuppressWarnings("unchecked")
	public List<ToDo> findAllToDos() {
		Criteria criteria = createEntityCriteria();
		return (List<ToDo>) criteria.list();
		/*CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<ToDo> criteria = builder.createQuery(ToDo.class);
		Root<ToDo> from = criteria.from(ToDo.class);
		CriteriaQuery<ToDo> select = criteria.select(from);
		TypedQuery<ToDo> typedQuery = getSession().createQuery(select);
		return typedQuery.getResultList();*/

	}

	public ToDo findToDoById(int id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (ToDo) criteria.uniqueResult();
/*
		return getSession().get(ToDo.class,id);
*/
	}

	public void doneToDoById(int id) {
		ToDo toDo = (ToDo) getSession().load(ToDo.class, id);
		toDo.setDone(true);
		getSession().update(toDo);
	}

	@SuppressWarnings("unchecked")
	public List<ToDo> findDoneToDos() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("done", true));
		return (List<ToDo>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<ToDo> findActualToDos() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("done", false));
		return (List<ToDo>) criteria.list();
	}
}
