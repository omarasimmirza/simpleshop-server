package paradigmsoft.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface IImagesRepository {
	List<String> getImage(int product_id);
}
