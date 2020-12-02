package es.upm.dit.apsv.cris.dao;

import java.util.List;

import org.hibernate.Session;

import es.upm.dit.apsv.cris.model.Researcher;

public class ResearcherDAOImplementation implements ResearcherDAO {

	private static ResearcherDAOImplementation instance = null;
	private ResearcherDAOImplementation() {}
	public static ResearcherDAOImplementation getInstance() {
		if(null == instance)
			instance = new ResearcherDAOImplementation();
		return instance;
	}
	
	@Override
	public Researcher create(Researcher researcher) {
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.save(researcher);
			session.getTransaction().commit();;
		}catch(Exception e) {
		}finally {
			session.close();
		}
		return researcher;
	}

	@Override
	public Researcher read(String researcherId) {
		Session session = SessionFactoryService.get().openSession();
		Researcher researcher = null;
		try {
			session.beginTransaction();
			researcher = session.get(Researcher.class, researcherId);
			session.getTransaction().commit();;
		}catch(Exception e) {
		}finally {
			session.close();
		}
		return researcher;
	}

	@Override
	public Researcher update(Researcher researcher) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(researcher);
			session.getTransaction().commit();;
		}catch(Exception e) {
		}finally {
			session.close();
		}
		return researcher;
	}

	@Override
	public Researcher delete(Researcher researcher) {
		
		Session session = SessionFactoryService.get().openSession();
		try {
			session.beginTransaction();
			session.delete(researcher);
			session.getTransaction().commit();;
		}catch(Exception e) {
		}finally {
			session.close();
		}
		return researcher;
	}

	@Override
	public List<Researcher> readAll() {
		
		Session session = SessionFactoryService.get().openSession();
		List <Researcher> l= null; 
		try {
			session.beginTransaction();
			l = (List <Researcher>) session.createQuery("from Researcher").getResultList();
			session.getTransaction().commit();;
		}catch(Exception e) {
		}finally {
			session.close();
		}
		return l;
	}

	@Override
	public Researcher readByEmail(String email) {
		
		for(Researcher r: this.readAll())
			if (email.equals(r.getEmail()))
				return r;
		return null;
	}

}
