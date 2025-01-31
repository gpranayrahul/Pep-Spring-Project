package com.example.entity;

import javax.persistence.*;

/**
 * This is a class that models a Message.
 *
 * You should NOT make any modifications to this class.
 */
@Entity
@Table(name="message")
public class Message {
     /**
     * An id for this message which will be automatically generated by the database.
     */
     @Column (name="messageId")
     @Id @GeneratedValue
    private Integer messageId;
    /**
     * The id for the user who has posted this message. We will assume that this is provided by the front-end of this
     * application.
     */
    @Column (name="postedBy")
    private Integer postedBy;
    /**
     * The text for this message- eg "this is my first post!". Must be not blank and under 255 characters
     */
    @Column (name="messageText")
    private String messageText;
    /**
     * The epoch time when this tweet was posted (number of seconds since Jan 1, 1970). Longs are large enough
     * to store this number. We will assume that this number is provided by the front-end of this application.
     */
    @Column (name="timePostedEpoch")
    private Long timePostedEpoch;
    /**
     * A default, no-args constructor, as well as correctly formatted getters and setters, are needed for
     * Jackson Objectmapper to work.
     */
    public Message(){
    }
    /**
     * When posting a new message, the id can be generated by the database. In that case, a constructor without
     * messageId is needed.
     * @param postedBy
     * @param messageText
     * @param timePostedEpoch
     */
    public Message(Integer postedBy, String messageText, Long timePostedEpoch) {
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }
    /**
     * Whem retrieving a message from the database, all fields will be needed. In that case, a constructor with all
     * fields is needed.
     * @param messageId
     * @param postedBy
     * @param messageText
     * @param timePostedEpoch
     */
    public Message(Integer messageId, Integer postedBy, String messageText, Long timePostedEpoch) {
        this.messageId = messageId;
        this.postedBy = postedBy;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @return messageId
     */
    public Integer getMessageId() {
        return messageId;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @param messageId
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @return postedBy
     */
    public Integer getPostedBy() {
        return postedBy;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @param postedBy
     */
    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @return messageText
     */
    public String getMessageText() {
        return messageText;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @param messageText
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @return timePostedEpoch
     */
    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @param timePostedEpoch
     */
    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
    /**
     * Overriding the default equals() method adds functionality to tell when two objects are identical, allowing
     * Assert.assertEquals and List.contains to function.
     * @param o the other object.
     * @return true if o is equal to this object.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (messageText == null) {
			if (other.messageText != null)
				return false;
		} else if (!messageText.equals(other.messageText))
			return false;
		if (postedBy == null) {
			if (other.postedBy != null)
				return false;
		} else if (!postedBy.equals(other.postedBy))
			return false;
		if (timePostedEpoch == null) {
			if (other.timePostedEpoch != null)
				return false;
		} else if (!timePostedEpoch.equals(other.timePostedEpoch))
			return false;
		return true;
	}
	
    /**
     * Overriding the default toString() method allows for easy debugging.
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", postedBy=" + postedBy +
                ", messageText='" + messageText + '\'' +
                ", timePostedEpoch=" + timePostedEpoch +
                '}';
    }


}
