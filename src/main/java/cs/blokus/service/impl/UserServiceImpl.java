package cs.blokus.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs.blokus.dao.UserDAO;
import cs.blokus.dto.UserDTO;
import cs.blokus.entity.User;
import cs.blokus.exceptions.DataDuplicateException;
import cs.blokus.service.IUserService;
import cs.blokusencryption.Encryption;

@Component
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserDAO userDAO;

	public UserDTO create(UserDTO user) throws DataDuplicateException {
		System.out.println(this.userDAO.findByUsername("a"));
		if(this.userDAO.findByUsername(user.getUsername()) != null) {
			throw new DataDuplicateException("The username already exists");
		}
		if(this.userDAO.findByEmail(user.getEmail()) != null) {
			throw new DataDuplicateException("The email is already in use");
		}
						
		user.setPassword(Encryption.encryptPassword(user.getPassword()));
		User usr = convertToEntity(user);
		return convertToDto(userDAO.save(usr));
	}

	public UserDTO findByEmail(String email) {
		return convertToDto(userDAO.findByEmail(email));
	}
	
	

	private UserDTO convertToDto(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
	    //userDTO.setRol(modelMapper.map(user.getRol(), RolesEnum.class));
	    return userDTO;
	}
	
	private User convertToEntity(UserDTO userDto) {
	    User user = modelMapper.map(userDto, User.class);
	  //  user.setRol(modelMapper.map(userDto.getRol(), Role.class));
	    return user;
	}
}
