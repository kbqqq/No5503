package cn.edu.hbuas.audiocms.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.hbuas.connect.HibernateSessionFactory;
import cn.edu.hbuas.models.Manager;

public class ManagerDao {
	public void addManagerNumber(int id) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Manager login = (Manager) session.load(Manager.class, id);

			login.setNumber(login.getNumber() + 1);
			session.update(login);
			tx.commit();
		} catch (Exception e) {
			System.out.println("���µ�¼��������" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public Manager getLogin(String account, String password) {
		Session session = null;
		String hql = "from Manager where account='" + account + "' and password='" + password + "'";
		Manager manager = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(hql);
			manager = (Manager) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
		return manager;
	}

	public void updatePassword(Manager manager) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			Manager newManager = (Manager) session.get(Manager.class, 1);
			newManager.setPassword(manager.getPassword());
			session.update(newManager);
			tx.commit();
		} catch (Exception e) {
			System.out.println("�޸Ĺ���Ա����");
			e.printStackTrace();
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
	public boolean validatePassword(String account, String password) {
		Session session = null;
		String hql = "from Manager where account='" + account + "' and password='" + password + "'";
		Manager manager = null;
		try {
			session = HibernateSessionFactory.getSession();
			Query query = session.createQuery(hql);
			manager = (Manager) query.uniqueResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		HibernateSessionFactory.closeSession();
		return manager!=null;
	}
}
