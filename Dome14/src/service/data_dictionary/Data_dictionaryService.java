package service.data_dictionary;
import java.util.List;
import pojo.Data_dictionary;

public interface Data_dictionaryService {
	
	List<Data_dictionary> getDictionary(String typeCode);

}
