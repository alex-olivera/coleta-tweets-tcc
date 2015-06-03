package br.ufc.si.tcc.coletaTweetsTcc.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "searches")
public class Search {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String description;
	private String valueSearch;
	private Date dateCreation;
	private long timeSearch;
	private int quantity;
	private int numberSearch;
	private boolean status;
	
	@OneToMany(mappedBy="search")
	private List<Tweet> tweets;
	
	public Search(){	
	}
	
	public Search(String description, String valueSearch) {
		this.description = description;
		this.valueSearch = valueSearch;
		this.status = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValueSearch() {
		return valueSearch;
	}

	public void setValueSearch(String valueSearch) {
		this.valueSearch = valueSearch;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public long getTimeSearch() {
		return timeSearch;
	}

	public void setTimeSearch(long timeSearch) {
		this.timeSearch = timeSearch;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getNumberSearch() {
		return numberSearch;
	}

	public void setNumberSearch(int numberSearch) {
		this.numberSearch = numberSearch;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCreation == null) ? 0 : dateCreation.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + numberSearch;
		result = prime * result + quantity;
		result = prime * result + (status ? 1231 : 1237);
		result = prime * result + (int) (timeSearch ^ (timeSearch >>> 32));
		result = prime * result + ((tweets == null) ? 0 : tweets.hashCode());
		result = prime * result
				+ ((valueSearch == null) ? 0 : valueSearch.hashCode());
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
		Search other = (Search) obj;
		if (dateCreation == null) {
			if (other.dateCreation != null)
				return false;
		} else if (!dateCreation.equals(other.dateCreation))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (numberSearch != other.numberSearch)
			return false;
		if (quantity != other.quantity)
			return false;
		if (status != other.status)
			return false;
		if (timeSearch != other.timeSearch)
			return false;
		if (tweets == null) {
			if (other.tweets != null)
				return false;
		} else if (!tweets.equals(other.tweets))
			return false;
		if (valueSearch == null) {
			if (other.valueSearch != null)
				return false;
		} else if (!valueSearch.equals(other.valueSearch))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Search [id=" + id + ", description=" + description
				+ ", valueSearch=" + valueSearch + ", dateCreation="
				+ dateCreation + ", timeSearch=" + timeSearch + ", quantity="
				+ quantity + ", numberSearch=" + numberSearch + ", status="
				+ status + ", tweets=" + tweets + "]";
	}
	
}
