package paradigmsoft.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paradigmsoft.repository.interfaces.IImagesRepository;

@Repository
public class ImagesRepository implements IImagesRepository{
	
	@Autowired
	private JdbcTemplate actions;

	@Override
	public List<String> getImage(int product_id) {
		var sql = "select img_url from images where product_id = " + product_id;
		return actions.queryForList(sql, String.class);
	}

}
