package many_to_one_sts.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import many_to_one_sts.dto.School;

public class SchoolMain {

	public static void main(String[] args) {
		
		School school=new School();
		
		school.setName("DPS");
		school.setAddress("Delhi");
		
		EntityManagerFactory entityManagerFactory =Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		EntityTransaction  entityTransaction=entityManager.getTransaction();
		
		entityTransaction.begin();
		
		entityManager.persist(school);
		
		entityTransaction.commit();
		
		

	}

}
