package cs.blokus.controller.rest;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.blokus.dto.UserDTO;
import cs.blokus.exceptions.DataDuplicateException;
import cs.blokus.security.jwt.JwtProvider;
import cs.blokus.security.message.request.LoginForm;
import cs.blokus.security.message.response.JwtResponse;
import cs.blokus.service.IUserService;


@RestController
@RequestMapping("user")
public class UserController {
		private final IUserService userService;

	    @Autowired
	    public UserController(IUserService userService) {
	        this.userService = userService;
	    }
	    
	    @Autowired
		private AuthenticationManager authenticationManager;
	   /* 
	    @Autowired
	    private SquareCoordsDAO scd; */
	    

		@Autowired
		JwtProvider jwtProvider;
		
//		private void populateSquareCoords() {
//			this.scd.save(new SquareCoords("top0Left0", 0, 0));
//			this.scd.save(new SquareCoords("top0Left1", 0, 1));
//			this.scd.save(new SquareCoords("top0Left2", 0, 2));
//			this.scd.save(new SquareCoords("top0Left3", 0, 3));
//			this.scd.save(new SquareCoords("top0Left4", 0, 4));
//			
//			this.scd.save(new SquareCoords("top1Left0", 1, 0));
//			this.scd.save(new SquareCoords("top1Left1", 1, 1));
//			this.scd.save(new SquareCoords("top1Left2", 1, 2));
//			this.scd.save(new SquareCoords("top1Left3", 1, 3));
//			
//			this.scd.save(new SquareCoords("top2Left0", 2, 0));
//			this.scd.save(new SquareCoords("top2Left1", 2, 1));
//			this.scd.save(new SquareCoords("top2Left3", 2, 3));
//			
//			this.scd.save(new SquareCoords("top3Left2", 3, 2));
//		
//		}

	    @PostMapping("login")
	    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			System.out.println(authentication);

			String jwt = jwtProvider.generateJwtToken(authentication);
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
		
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
		}
	    

		@GetMapping("getByEmail/{email}")
		public ResponseEntity<UserDTO> getByEmail(@PathVariable String email) {
		        try {
		            return ResponseEntity.ok(userService.findByEmail(email));
		        } catch (Exception e) {
		            e.printStackTrace();
		            return ResponseEntity.ok(null);
		        }
		    }
		
	
		
		@PostMapping("register")
		public UserDTO register(@RequestBody UserDTO user) throws DataDuplicateException { 
			return userService.create(user);	
		}   
}
