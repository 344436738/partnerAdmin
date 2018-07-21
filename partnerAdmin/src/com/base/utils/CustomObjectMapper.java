package com.base.utils;

import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.codehaus.jackson.JsonGenerator;  
import org.codehaus.jackson.JsonProcessingException;  
import org.codehaus.jackson.map.JsonSerializer;  
import org.codehaus.jackson.map.ObjectMapper;  
import org.codehaus.jackson.map.SerializerProvider;  
import org.codehaus.jackson.map.ser.CustomSerializerFactory;  

public class CustomObjectMapper extends ObjectMapper{
	
	/**
	 * 解决Date类型返回json格式自定义格式
	 */
	public CustomObjectMapper(){  
		CustomSerializerFactory factory = new CustomSerializerFactory();  
    	factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){  
    		@Override  
	        public void serialize(Date value,JsonGenerator jsonGenerator,SerializerProvider provider)throws IOException,JsonProcessingException {  
	        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	            jsonGenerator.writeString(sdf.format(value));  
	        }  
        });  
        this.setSerializerFactory(factory);  
    }  
}