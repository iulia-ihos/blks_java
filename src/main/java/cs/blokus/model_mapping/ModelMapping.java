package cs.blokus.model_mapping;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelMapping {
	
	@Autowired
	private ModelMapper modelMapper;


	/*@SuppressWarnings("unchecked")
	public Object convertToDto(Object entity) throws InstantiationException, IllegalAccessException, 
			ClassNotFoundException {
		Class<? extends Object> entityClass = entity.getClass();
		Class<? extends Object> dtoClass = Class.forName(this.DTO_CLASSPATH + "." + entityClass.getSimpleName()+"DTO");
		
		Object dto = dtoClass.newInstance();
		
		Field[] fields = ((Class<? extends Object>) entityClass).getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String fieldName = fields[i].getName();
			String getterName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
			Type type = fields[i].getGenericType();
		   System.out.println("The field is: " + fields[i].getName());
		   System.out.println("type: " + fields[i].getType());
		   
		 
		try {
			Object value = entityClass.getMethod(getterName).invoke(entity);
			  System.out.println("value: " + value);
			 
				  
				  if (type instanceof ParameterizedType) {
			            ParameterizedType pt = (ParameterizedType) type;
			            System.out.println("raw type: " + pt.getRawType());
			            System.out.println("owner type: " + pt.getOwnerType());
			            System.out.println("actual type args:");
			            for (Type t : pt.getActualTypeArguments()) {
			                System.out.println("    " + t);
			            }
			        }
				 
//				   for(Object object: )
			   
		} catch (Exception e) {
			//
			e.printStackTrace();
		}
		  // System.out.println("object: " + o);
		 
		}
		
		Field[] fields = ((Class<? extends Object>) dto).getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
		   System.out.println("The field is: " + fields[i].toString());
		}
		
		//TileSquareDTO dto = modelMapper.map(user, TileSquareDTO.class);
	    //userDTO.setRol(modelMapper.map(user.getRol(), RolesEnum.class));
	    return null;
	}
	*/
	
	public <T extends Object> T map(Object object, Class<T> type) {
		T mappedObject = modelMapper.map(object, type);
	    //userDTO.setRol(modelMapper.map(user.getRol(), RolesEnum.class));
	    return mappedObject;
	}
	
	/*public Object convertToEntity(Object dto) {
		//TileSquareDTO entity = modelMapper.map(dto, TileSquare.class);
	  //  user.setRol(modelMapper.map(userDto.getRol(), Role.class));
	    return null;
	}*/
}
