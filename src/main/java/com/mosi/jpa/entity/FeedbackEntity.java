package com.mosi.jpa.entity;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * Feedback Entity class which represent stored object.
 * <p>
 * Object contains user's name with feedback message. ID and CreateDate is
 * assigned automatically on creation
 * </p>
 * 
 * @author Martin
 *
 */
@Entity
public class FeedbackEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String user;

	@Column(nullable = false)
	private String message;

	@Column
	private ZonedDateTime createDate;

	public FeedbackEntity() {

	}

	public FeedbackEntity(String user, String message)
	{
		this.user = user;
		this.message = message;
	}
	
	@PrePersist
	protected void onCreate() {
		createDate = ZonedDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setCreateDate(ZonedDateTime createDate) {
		this.createDate = createDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ZonedDateTime getCreateDate() {
		return createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		FeedbackEntity other = (FeedbackEntity) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FeedbackEntity [id=" + id + ", user=" + user + ", message=" + message + ", createDate=" + createDate
				+ "]";
	}
}
