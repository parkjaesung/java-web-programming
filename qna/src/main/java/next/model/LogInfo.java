package next.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("loginfo")
public class LogInfo {
	private String writer;
	
	private String message;
	
	private Date createdDate;
	
	public LogInfo() {
	}

	public LogInfo(String writer, String message, Date createdDate) {
		this.writer = writer;
		this.message = message;
		this.createdDate = createdDate;
	}
	
	public String getWriter() {
		return writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogInfo other = (LogInfo) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LogInfo [writer=" + writer + ", message=" + message
				+ ", createdDate=" + createdDate + "]";
	}
}
