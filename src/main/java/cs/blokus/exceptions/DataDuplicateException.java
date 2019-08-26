package cs.blokus.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataDuplicateException extends Exception{
	public DataDuplicateException(String message) {
		super(message);
	}

}
