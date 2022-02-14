package paradigmsoft.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.Authenticate;
import paradigmsoft.model.User;
import paradigmsoft.repository.interfaces.IUserRepository;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	private JdbcTemplate actions;

	@Override
	public void createUser(User user) {
		var sql = "insert into user (first_name, last_name, username, password, email, street_address, city, state, zipcode) values (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		actions.update(sql, user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getPassword(), user.getEmail(), user.getStreet_address(), user.getCity(), user.getState(), user.getZipcode());
	}

	@Override
	public boolean login(Authenticate user) {
		var sql = "select username from user where password = ?";
		String object = actions.queryForObject(sql, String.class, new Object[] {user.getPassword()});
		
		if(object.equals(user.getUsername())) {
			return true;
		}
		return false;
	}

	@Override
	public int getUserId(String username) {
		var sql = "select user_id from user where username = ?";
		int id = actions.queryForObject(sql, Integer.class, new Object [] {username});
		return id;
	}

}