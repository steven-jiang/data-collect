package com.kii.datacollect.store;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class SparkDataEntity implements Serializable{

	private String id;

	private String source;

	private String sourceType;

	private String actionType;

	private String action;


	private Timestamp timeStamp;

	private String from;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SparkDataEntity that = (SparkDataEntity) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(timeStamp, that.timeStamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, timeStamp);
	}
}
