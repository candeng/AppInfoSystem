package service.data_dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.data_dictionary.Data_dictionaryMapper;

import pojo.Data_dictionary;

@Service("data_dictionaryService")
public class Data_dictionaryServiceImpl implements Data_dictionaryService {
	@Resource
	private Data_dictionaryMapper data_dictionaryMapper;
	
	public List<Data_dictionary> getDictionary(String typeCode) {
		// TODO Auto-generated method stub
		return data_dictionaryMapper.getDictionary(typeCode);
	}

}
