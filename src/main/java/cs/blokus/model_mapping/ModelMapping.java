package cs.blokus.model_mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapping {
	
	@Autowired
	private ModelMapper modelMapper;

	
	public <T extends Object> T map(Object object, Class<T> type) {
		T mappedObject = modelMapper.map(object, type);
	    return mappedObject;
	}

}
