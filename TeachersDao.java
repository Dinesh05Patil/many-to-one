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

public class TeachersDao {
	
	Scanner scanner=new Scanner(System.in);
	
	public EntityManager gEntityManager() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
	
	public void saveTeacher(Teachers teachers, int schoolid) {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		School school=entityManager.find(School.class, schoolid);
		
		
	//	School school=students.getSchool();
		entityTransaction.begin();
		entityManager.persist(teachers);
		//entityManager.persist(school);
		entityTransaction.commit();
	}
	public void updateTeacher(int id,Teachers teachers) {
		
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		System.out.println("Enter the name to be updated");
		String name=scanner.next();
		
		Teachers receivedTeacher=entityManager.find(Teachers.class, id);
		receivedTeacher.setName(name);
		teachers.setId(id);
		if(receivedTeacher!=null) {
			entityTransaction.begin();
			entityManager.merge(receivedTeacher);
			//entityManager.merge(students.getSchool());
			entityTransaction.commit();
		}
		else {
			System.out.println("teachers id not found");
		}
		
		
	}
	public void deleteTeacher(int id) {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Teachers receivedTeacher=entityManager.find(Teachers.class, id);
		entityTransaction.begin();
		entityManager.remove(receivedTeacher);
		//entityManager.remove(receivedStudent.getSchool());
		entityTransaction.commit();
		
	}
	public void getById(int id){
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Teachers receivedTeacher=entityManager.find(Teachers.class, id);
		System.out.println(receivedTeacher);
		
	}
	public void getAll() {
		EntityManager entityManager=gEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		Query query=entityManager.createQuery("select t from Teachers t");
		
		List<Teachers>list=query.getResultList();
		System.out.println(list);
		
	}


}
