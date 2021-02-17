package spring.persistence.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import spring.persistence.entity.History;

public interface RecogMapper {

	@Select("SELECT * FROM history")
	List<History> findAll();


	@Insert("INSERT INTO history(img, resultCat, resultDog, judge)"
			+ "VALUES(#{img}, #{resultCat}, #{resultDog}, #{judge})")
	@Options(useGeneratedKeys=true, keyColumn="id", keyProperty="id")
	void insertHistory(History history);
}
