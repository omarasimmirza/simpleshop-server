package paradigmsoft.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.model.User;
import paradigmsoft.repository.interfaces.IUserRepository;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	private JdbcTemplate actions;

	@Override
	public List<User> userList() {
		var sql = "select * from user";
		return actions.query(sql, (rs, rowNum) -> 
			new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("username"), rs.getString("password"), rs.getString("email"),
					rs.getString("street_address"), rs.getString("city"), rs.getString("state"),
					rs.getInt("zipcode")));
	}

	@Override
	public void createUser(User user) {
		var sql = "insert into user (first_name, last_name, username, password, email, street_address, city, state, zipcode)";
		actions.update(sql, user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getPassword(), user.getEmail(), user.getStreet_address(), user.getCity(), user.getState(), user.getZipcode());
	}

	@Override
	public User getUser(int id) {
		var sql = "select * from user where user_id = " + id;
		return actions.queryForObject(sql, (rs, rowNum) ->
			new User(rs.getInt("user_id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("username"), rs.getString("password"), rs.getString("email"),
					rs.getString("street_address"), rs.getString("city"), rs.getString("state"),
					rs.getInt("zipcode")));
	}

	@Override
	public void deleteUser(int id) {
		var sql = "delete from users where user_id = ?";
		
		actions.update(sql, new Object[] {id});
	}

	@Override
	public void updateUser(int id, User user) {
	}

	@Override
	public boolean login(User user) {
		var sql = "select username from user where password = ?";
		String object = actions.queryForObject(sql, String.class, new Object[] {user.getPassword()});
		
		if(object.equals(user.getUsername())) {
			return true;
		}
		return false;
	}

}