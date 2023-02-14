package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import wissen.rest.model.Employee;
import wissen.rest.repository.EmployeeRepository;


public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Employee emp = empRepo.findByEmail(username);
		if(emp==null)
			throw new UsernameNotFoundException("Employee not found");
		
		return new UserPrincipal(emp);
	}

}
