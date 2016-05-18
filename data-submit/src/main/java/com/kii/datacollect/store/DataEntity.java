package com.kii.datacollect.store;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;

public class DataEntity implements Serializable{

	private String id;

	private String source;

	private String sourceType;

	private String actionType;

	private String action;

	private JsonNode data;

	private long timeStamp;

	private Set<String>  target;

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

	public void setAction(String actionDetail) {
		this.action = actionDetail;
	}


	public JsonNode getData() {
		return data;
	}

	@JsonRawValue
	public void setData(JsonNode data) {
		this.data = data;
	}

	@JsonProperty("timestamp")
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Set<String> getTarget() {
		return target;
	}

	public void setTarget(Set<String> target) {
		this.target = target;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DataEntity that = (DataEntity) o;
		return timeStamp == that.timeStamp &&
				Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, timeStamp);
	}
}
