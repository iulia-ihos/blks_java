package cs.blokus.service;

import org.springframework.stereotype.Service;

import cs.blokus.dto.UserDTO;
import cs.blokus.exceptions.DataDuplicateException;

@Service
public interface IUserService {
	
	public UserDTO create(UserDTO user) throws DataDuplicateException;
	public UserDTO findByEmail(String email);
}
