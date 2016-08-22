package com.exercise.hibernate2.core;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
	    if (value == null) return "-";
	    else return value ? "YES" : "NO";
    }

	@Override
	public Boolean convertToEntityAttribute(String value) {
	    if (value.equals("-"))
	    	return null;
	    else if	(value.equals("YES")) 
	    	return true;
	    else if (value.equals("NO")) 
	    	return false;
	    else 
	    	throw new IllegalStateException("Invalid boolean character: " + value);
	    
	}

}

