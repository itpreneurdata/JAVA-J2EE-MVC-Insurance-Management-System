package in.co.insurance.mgt.model;


import static org.junit.jupiter.api.Assertions.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

import in.co.insurance.mgt.bean.UserBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DatabaseException;
import in.co.insurance.mgt.exception.DuplicateRecordException;


class UserModelTest{
	
	
	private UserModel user;
	private UserBean bean;
	
	void giveValueToBeanForAdd()
	{

		bean.setFirstName("DummyAdd");
		bean.setLastName("DummyAdd");
		bean.setLogin("DummyAdd");
		bean.setPassword("DummyAdd@123");
		bean.setEmail("DummyAdda@gmail.com");
		bean.setContactNo("9685455858");
		bean.setGender("Male");
		bean.setProfile(null);
		bean.setRoleId(2);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
//		Date date = new Date(2021-05-10 18:30:38);
//		Timestamp timestamp = new Timestamp(date.getTime());
//		bean.setCreatedDatetime( new Timestamp(2021,05,18,18,30,38,0));
//		bean.setModifiedDatetime(new Timestamp(2021,05,18,18,30,38,0));
		
		
	}
	
	
	
	void giveValueToBeanForSerch()
	{
		
		bean.setId(1);
		bean.setFirstName("Bhagavati");
		bean.setLastName("Tiwari");
		bean.setLogin("Admin123");
		bean.setPassword("Admin@321");
		bean.setEmail("ritoqeka@mailinator.com");
		bean.setContactNo("9685455858");
		bean.setGender("Female");
		bean.setProfile(null);
		bean.setRoleId(1);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
//		Date date = new Date(2021-05-10 18:30:38);
//		Timestamp timestamp = new Timestamp(date.getTime());
//		bean.setCreatedDatetime( new Timestamp(2021,05,18,18,30,38,0));
//  	bean.setModifiedDatetime(new Timestamp(2021,05,18,18,30,38,0));
		
		
	}
	
	
	@BeforeAll
	 static void beforeAllInit()
	{
		System.out.println("Start Testing DataModal  ...\n");
	}
	
	
	@BeforeEach
	void set(TestInfo testInfo,TestReporter testReporter){
		System.out.println("");
		testReporter.publishEntry("Start:"+ testInfo.getDisplayName());
		 user=new UserModel();
		 bean=new UserBean();
	}
	
	@AfterEach
	void cleanup(TestInfo testInfo,TestReporter testReporter)
	{
		System.out.println("");
		testReporter.publishEntry("End:"+ testInfo.getDisplayName());
		
	}
	
	@AfterAll
	 static void complete()
	{
		System.out.println("End Testing DataModal....\n");
	}

	
	
	@DisplayName("NextPK Test")
	@Test
	void testNextPK() {
		
		try {
			assertEquals(7,user.nextPK(),"checking get highest user id");
		} 
		catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@DisplayName("ADD User Test")
	@Disabled
	@Test
	void testAdd() {
		giveValueToBeanForAdd();
		
		try {
			assertEquals(7,user.add(bean),"checking record added");
		} 
		catch (ApplicationException e) {
			
			e.printStackTrace();
		}
		catch (DuplicateRecordException e) {
			
			e.printStackTrace();
		}
	}
	
	@Disabled
	@Test
	@DisplayName("DuplicateRecordExcption Test")
	void testDuplicateRecordException() {
		
		bean.setLogin("Tanaya");
		assertThrows(DuplicateRecordException.class,()->user.add(bean),"checking Custom Exception");
	
	}

	@DisplayName("DELETE Record Test")
	@Test
	void testDelete() {
		
		try {
			user.delete(bean);
		} 
		catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception occurred during delete: " + e.getMessage());
		}
		
		
	}

	
	@Test
	@DisplayName("SEARCH BY LOGIN Test")
	void testFindByLogin() {
		
		giveValueToBeanForSerch();
		assertAll("",()->assertEquals(bean.getLogin(),user.findByLogin("Admin123").getLogin()),
				     ()->assertEquals(bean.getPassword(),user.findByLogin("Admin123").getPassword())
				);
		
		
	}

	@Test
	void testFindByPK() {
		
		giveValueToBeanForSerch();
		assertAll("",()->assertEquals(bean.getLogin(),user.findByPK(1).getLogin()),
				     ()->assertEquals(bean.getPassword(),user.findByPK(1).getPassword())
				);
		
	}

/*	@DisplayName("UPADATE Test")
	@Test
	void testUpdate() {
		giveValueToBeanForAdd();
		bean.setLogin("DummyAddU");
		bean.setPassword("DummyAddU@123");
		try {
			user.add(bean);
			user.update(bean);
		} catch (ApplicationException | DuplicateRecordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertAll("",()->assertEquals(bean.getLogin(),user.findByPK(bean.getId()).getLogin()),
				     ()->assertEquals(bean.getPassword(),user.findByPK(bean.getId()).getPassword())
				);
	}*/


	@DisplayName("SEARCH Record Test")
	@Test
	void testSearchUserBeanIntInt() {
		
		ArrayList<UserBean> list=new ArrayList<UserBean>();
		list.add(bean);
		giveValueToBeanForSerch();
		try
		{
			assertEquals(list.size(),user.search(bean, 1, 1).size());
		} 
		catch (ApplicationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}


	@Test
	@DisplayName("Display Record Test")
	void testListIntInt() {
		
		ArrayList<UserBean> list=new ArrayList<UserBean>();
		list.add(bean);
		giveValueToBeanForSerch();
		try
		{
			assertEquals(list.size(),user.list(1, 1).size());
		} 
		catch (ApplicationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@DisplayName("Authenticate User Test")
	@Test
	void testAuthenticate() {
		
		giveValueToBeanForSerch();
		assertAll("",()->assertEquals(bean.getLogin(),user.authenticate("Admin123","Admin@321").getLogin()),
				     ()->assertEquals(bean.getPassword(),user.authenticate("Admin123","Admin@321").getPassword())
				);
		
		
	}

}
