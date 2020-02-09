package cs.blokus.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.UserDAO;
import cs.blokus.dto.UserDTO;
import cs.blokus.encryption.Encryption;
import cs.blokus.entity.User;
import cs.blokus.exceptions.DataDuplicateException;
import cs.blokus.service.IUserService;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserDAO userDAO;

	public UserDTO create(UserDTO user) throws DataDuplicateException {

		if(this.userDAO.findByUsername(user.getUsername()) != null) {
			throw new DataDuplicateException("The username already exists");
		}
		if(this.userDAO.findByEmail(user.getEmail()) != null) {
			throw new DataDuplicateException("The email is already in use");
		}
						
		user.setPassword(Encryption.encryptPassword(user.getPassword()));
		User usr = mapper.map(user, User.class);
		return mapper.map(userDAO.save(usr), UserDTO.class);
	}

	@Override
	public UserDTO findByUsername(String username) {
		return mapper.map(userDAO.findByUsername(username), UserDTO.class);
	}
	
	@Override
	public boolean checkUsername(String username) {
		if(userDAO.findByUsername(username) == null)
			return false;
		return true;
	}
}
