package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.User;

@Repository
public interface IUserRepository {
	List<User> userList();
	void createUser(User user);
	User getUser(int id);
	void deleteUser(int id);
	void updateUser(int id, User user);
	boolean login(User user);
}
