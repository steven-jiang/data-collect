package com.kii.datacollect.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonMapper {


	final ObjectMapper mapper;

	public JsonMapper(){
		this.mapper=createDefaultMapper();
	}
	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		result.configure(SerializationFeature.INDENT_OUTPUT, true);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

		return result;
	}


	public <T>  T readValue(String input, Class<T> cls) {

		try {
			return mapper.readValue(input,cls);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}
	}

	public String writeValueAsString(Map<String, Object> map) {

		try {
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}
	}

	public String writeObjectAsString(Object entity) {

		try {
			return mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}	}

	public Map<String,Object> readValueToMap(String json) {
		try {
			return mapper.readValue(json,Map.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}
	}

	public JsonNode readStream(Resource resource) {


		try {
			return mapper.readValue(resource.getInputStream(),JsonNode.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}

	}

	public String flatJson(String jsonStr) throws IOException {

			JsonNode node=mapper.readValue(jsonStr,JsonNode.class);

			Map<String,Object> result=new HashMap<>();

			fillMap(result,node,null);

		    return mapper.writer().without(SerializationFeature.INDENT_OUTPUT).writeValueAsString(result);
	}

	private  void fillMap(Map<String,Object> result,JsonNode source,String prefix){

		if (source.isPojo()||source.isObject()) {

			for(Iterator<Map.Entry<String,JsonNode>> iter = source.fields(); iter.hasNext();){

				Map.Entry<String,JsonNode>  entry=iter.next();

				fillMap(result,entry.getValue() , getNextPrefix(prefix,entry.getKey()));
			}

		}else if(source.isArray()){

			AtomicInteger idx = new AtomicInteger(0);

			source.forEach((s)->{
				fillMap(result, s,getNextPrefix(prefix,idx.incrementAndGet()));
			});

		}else {

			if(source.isNumber()){
				result.put(prefix,source.numberValue());
			}else if(source.isBoolean()){
				result.put(prefix,source.booleanValue());
			}else if(source.isTextual()){
				result.put(prefix,source.asText());
			}else{
				result.put(prefix,source.toString());
			}
		}

		return;
	}


	private static String getNextPrefix(String prefix,Object curr){
		if(prefix==null){

			return String.valueOf(curr);
		}else{
			return prefix+"."+curr;
		}
	}


	private Pattern linePat=Pattern.compile("\\}\\s*(,|\r\n)\\s*\\{",Pattern.MULTILINE);

	public String[] splitMuiJson(String jsonList){

		if(linePat.matcher(jsonList).find()) {


			String[]  jsonArray= linePat.split(jsonList);

			for(int i=0;i<jsonArray.length;i++){

				if(i==0){
					jsonArray[i]=jsonArray[i]+"}";
				}else if(i==jsonArray.length-1){
					jsonArray[i]="{"+jsonArray[i];
				}else{
					jsonArray[i]="{"+jsonArray[i]+"}";
				}
			}

			return jsonArray;
		}else{
			return new String[]{jsonList};
		}

	}

}
