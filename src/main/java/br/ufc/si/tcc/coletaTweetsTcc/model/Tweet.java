package br.ufc.si.tcc.coletaTweetsTcc.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tweets")
public class Tweet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private long id;
	
	private String text;
	
	@ElementCollection
	@CollectionTable (name = "mentioned_tweets", joinColumns = @JoinColumn(name = "tweet_id"))
	@Column (name="mentionad")
	private Collection<String> mentioned;
	
	@ElementCollection
	@CollectionTable (name="hash_tags_tweets", joinColumns = @JoinColumn(name = "tweet_id"))
	@Column (name="hash_tag")
	private Collection<String> hashTags;
	
	private Date date;
	private double latitude;
	private double longitude;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private UserTwitter user;
	
	@ManyToOne
	@JoinColumn(name="search_id")
	private Search search;
	
	public Tweet(){
		
	}
	
	public Tweet(long id, String text, Collection<String> mentioned,
			Collection<String> hashTags, Date date, double latitude,
			double longitude, UserTwitter user) {
		super();
		this.id = id;
		this.text = text;
		this.mentioned = mentioned;
		this.hashTags = hashTags;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.user = user;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Collection<String> getMentioned() {
		return mentioned;
	}

	public void setMentioned(Collection<String> mentioned) {
		this.mentioned = mentioned;
	}

	public Collection<String> getHashTags() {
		return hashTags;
	}


	public void setHashTags(Collection<String> hashTags) {
		this.hashTags = hashTags;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public UserTwitter getUser() {
		return user;
	}

	public void setUser(UserTwitter user) {
		this.user = user;
	}
	
	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((search == null) ? 0 : search.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((hashTags == null) ? 0 : hashTags.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((mentioned == null) ? 0 : mentioned.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Tweet other = (Tweet) obj;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (hashTags == null) {
			if (other.hashTags != null)
				return false;
		} else if (!hashTags.equals(other.hashTags))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(latitude) != Double
				.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double
				.doubleToLongBits(other.longitude))
			return false;
		if (mentioned == null) {
			if (other.mentioned != null)
				return false;
		} else if (!mentioned.equals(other.mentioned))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}