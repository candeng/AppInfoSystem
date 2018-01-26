package dao.data_dictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.Data_dictionary;

public interface Data_dictionaryMapper {
	
	List<Data_dictionary> getDictionary(@Param("typeCode")String typeCode);
}
