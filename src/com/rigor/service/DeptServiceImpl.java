package com.rigor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.model.Department;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;;

@Service("deptService")
@Transactional
public class DeptServiceImpl implements DeptService {
	SessionFactory sessionFactory = null;

	public DeptServiceImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();

	}

	// ------------Save Department master data-----------------------
	public Department saveDept(Department dept) {
		Department dept_new = new Department();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			dept_new = dept;
			Methods method = new Methods();
			dept_new.setDept_id(method.generateID(session, "D", "dept_id", Department.class));
			dept_new.setStatus(1);
			session.save(dept_new);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return dept_new;
	}

	// ----------------Retrieve All Department master data---------------
	@Override
	public List<Department> getAllDepts() {

		List<Department> list = null;
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			list = session.createCriteria(Department.class).list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return list;
	}
	// ----------------Retrieve Activated Department master data---------------
	@Override
	public List<Department> getDeptList() {
		
		List<Department> list = null;
		Session session = sessionFactory.openSession();
		
		try {
			session.beginTransaction();
			list = session.createCriteria(Department.class).add(Restrictions.eq("status",1)).list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return list;
	}

	// ----------------Update Department master data---------------

	@Override
	public void updateDept(Department dept) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			session.update(dept);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	@Override
	public Department findById(String dept_id) {

		List<Department> list = getAllDepts();

		for (Department dept : list) {
			if (dept.getDept_id().equals(dept_id)) {
				return dept;
			}
		}

		return null;
	}

	@Override
	public void deactivateDept(String id) {

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("UPDATE com.rigor.model.Department SET status=0 WHERE dept_id = :dept_id");
			query.setParameter("dept_id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
	}
	@Override
	public void activateDept(String id) {
		
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			Query query = session.createQuery("UPDATE com.rigor.model.Department SET status=1 WHERE dept_id = :dept_id");
			query.setParameter("dept_id", id);
			query.executeUpdate();
			
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		
	}
}
