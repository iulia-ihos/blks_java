package cs.blokus.entity.attribute_converter;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayConverter implements AttributeConverter<int[][], String> {
	
	@Autowired
	private  ObjectMapper objectMapper;
	 
    @Override
    public String convertToDatabaseColumn(int[][] board) {
 
        String boardJson = null;
        if(board == null) {
        	board = new int[20][20];
        }
        try {
           
			boardJson = objectMapper.writeValueAsString(board);
        } catch (final JsonProcessingException e) {
        	
        }
 
        return boardJson;
    }
 
    @Override
    public int[][] convertToEntityAttribute(String boardJSON) {
       int[][] board = null;
        try {
            board = objectMapper.readValue(boardJSON, int[][].class);
        } catch (final IOException e) {
           
        }
 
        return board;
    }
 
}