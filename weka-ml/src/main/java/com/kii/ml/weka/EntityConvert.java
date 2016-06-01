package com.kii.ml.weka;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class EntityConvert {

	public static Instance getInstances(Instances meta,Object entity){

		Instance inst=new DenseInstance(5);
		inst.setDataset(meta);


		PropertyDescriptor[] descList= PropertyUtils.getPropertyDescriptors(entity);


		for(int i=0;i<meta.numAttributes();i++) {

			Attribute attribute=meta.attribute(i);

			Object value= null;
			try {
				value = PropertyUtils.getProperty(entity,attribute.name());

			} catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
				e.printStackTrace();
				continue;
			}

			if(value instanceof  String) {
				inst.setValue(i, String.valueOf(value));
			}else if(value instanceof  Number){
				inst.setValue(i,((Number)value).doubleValue());
			}else {
				continue;
			}

		}

		return inst;


	}

	public static Instance getInstancesOld(Instances meta,Object entity){

		Instance inst=new DenseInstance(meta.numAttributes());
		inst.setDataset(meta);

		for(int i=0;i<meta.numAttributes();i++){

			Attribute  att=meta.attribute(i);

			if(att.name().equals("id")){
				break;
			}

			String value=null;
			try {

				value=BeanUtils.getProperty(entity, att.name());
			}catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException e) {
				break;
			}

			if (att.isNominal()) {
					int index = att.indexOfValue(value);
					inst.setValue(i, index);

			} else if (att.isNumeric()) {
					inst.setValue(i, Double.parseDouble(value));
			} else {
					throw new IllegalStateException("Unhandled attribute type!");
			}

		}

		return inst;


	}
}
