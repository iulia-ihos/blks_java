package cs.blokus.service;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import cs.blokus.dto.UserDTO;
import cs.blokus.enums.RolesEnum;
import cs.blokus.exceptions.DataDuplicateException;
import cs.blokus.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class UserServiceTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private IUserService userService;
	
	@Test
	@DatabaseSetup(value = "/user.xml")
	public void testCreateDuplicateUsername() throws DataDuplicateException {
		UserDTO user =  new UserDTO(1L, "email", "user1", "password", RolesEnum.PLAYER);
		thrown.expect(DataDuplicateException.class);
		thrown.expectMessage("username already exists");
		userService.create(user);
	}
	
	@Test
	@DatabaseSetup(value = "/user.xml")
	public void testCreateDuplicateEmail() throws DataDuplicateException {
		UserDTO user =  new UserDTO(1L, "email1", "user", "password", RolesEnum.PLAYER);
		thrown.expect(DataDuplicateException.class);
		thrown.expectMessage("email is already in use");
		userService.create(user);
	}
	
	@Test
	@DatabaseSetup(value = "/user.xml")
	public void testCreate() throws DataDuplicateException {
		UserDTO user =  new UserDTO(1L, "email", "user", "password", RolesEnum.PLAYER);
		UserDTO returnedUser = userService.create(user);
		assertEquals(user.getEmail(), returnedUser.getEmail());
		assertEquals(user.getUsername(), returnedUser.getUsername());
	}

}
