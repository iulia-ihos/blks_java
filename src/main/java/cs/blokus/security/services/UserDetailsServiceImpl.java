package cs.blokus.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cs.blokus.dao.UserDAO;
import cs.blokus.entity.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userDAO.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User Not Found with username : " + username);

		return UserDetailsImpl.build(user);
	}
}