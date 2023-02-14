package wissen.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wissen.rest.model.Employee;
import wissen.rest.repository.EmployeeRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeService employeeService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeService.findByEmail(username);
		if (employee == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new User(employee.getEmail(), employee.getPassword(),
				new ArrayList<>());
	}
	
	public Employee save(Employee employee) {
		employee.setPassword( new BCryptPasswordEncoder().encode(employee.getPassword()));
		employeeService.saveEmployee(employee);
		return employee;
	}
}