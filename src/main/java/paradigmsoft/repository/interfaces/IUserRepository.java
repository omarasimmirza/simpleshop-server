package paradigmsoft.repository.interfaces;

import org.springframework.stereotype.Repository;

import paradigmsoft.model.Authenticate;
import paradigmsoft.model.User;

@Repository
public interface IUserRepository {
	void createUser(User user);
	boolean login(Authenticate user);
	int getUserId(String username);
}
