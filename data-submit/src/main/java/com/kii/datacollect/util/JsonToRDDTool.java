//package com.kii.datacollect.util;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.spark.sql.types.DataType;
//import org.apache.spark.sql.types.DataTypes;
//import org.apache.spark.sql.types.StructField;
//import org.apache.spark.sql.types.StructType;
//
//import com.fasterxml.jackson.databind.JsonNode;
//
//public class JsonToRDDTool {
//
//
//
//
//
//	public static StructType generSchema(JsonNode node,List<StructField> fields){
//
//
////		List<StructField> fields = new ArrayList<StructField>();
//
//		if(node.isArray()){
//
//
//
//		}else {
//
//			for (Iterator<Map.Entry<String, JsonNode>> iter = node.fields(); iter.hasNext(); ) {
//
//				Map.Entry<String, JsonNode> entry = iter.next();
//
//				DataType type = getSparkType(entry.getValue(), entry.getKey());
//
//				fields.add(DataTypes.createStructField(entry.getKey(), type, true));
//			}
//		}
//
//
////		for (String fieldName: schemaString.split(" ")) {
////			fields.add(DataTypes.createStructField(fieldName, DataTypes.StringType, true));
////		}
////		StructType schema = DataTypes.createStructType(fields);
//
//
//	}
//
//	private static  DataType getSparkType(JsonNode node,String name ){
//
//		if(node.isTextual()){
//			return DataTypes.StringType;
//		}else if(node.isBoolean()){
//			return DataTypes.BooleanType;
//		}else if(node.isFloat()){
//			return DataTypes.FloatType;
//		}else if(node.isInt() &&  name.contains("time")){
//			return DataTypes.TimestampType;
//		}else {
//			return DataTypes.IntegerType;
//		}
//	}
//
//}
