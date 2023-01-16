package many_to_one_sts.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import many_to_one_sts.dto.School;
import many_to_one_sts.dto.Teachers;



public class SchoolDao {
	
	Scanner scanner=new Scanner(System.in);

	public EntityManager gEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		return entityManager;
	}

	public void saveSchool(School school) {
		EntityManager entityManager = gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		entityTransaction.begin();

		entityManager.persist(school);
		entityTransaction.commit();
	}

	public void updateSchool(int id, School school) {

		EntityManager entityManager = gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		System.out.println("Enter the name to be updated");
		String name=scanner.next();

		School receivedSchool = entityManager.find(School.class, id);
		receivedSchool.setName(name);
		school.setId(id);
		if (receivedSchool != null) {
			entityTransaction.begin();
			entityManager.merge(receivedSchool);

			entityTransaction.commit();
		} else {
			System.out.println("School  id not found");
		}

	}

	public void deleteSchool(int id) {
		EntityManager entityManager = gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		School receivedSchool = entityManager.find(School.class, id);
		entityTransaction.begin();
		entityManager.remove(receivedSchool);

		entityTransaction.commit();

	}

	public void getById(int id) {
		EntityManager entityManager = gEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		School receivedSchool = entityManager.find(School.class, id);
		System.out.println(receivedSchool);

	}
	public void getAll() {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Query query=entityManager.createQuery("select s from School s");
		
		List<Teachers>list=query.getResultList();
		System.out.println(list);
		
	}


}
