package com.dataservice.bo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

@QueryEntity
@Document(collection = "Book")
public class BookInfo
{
	private String id;
	private String title;
	private String description;
	private Double cost;
	private boolean isAvailable;
	private int noOfCopies;
	private String publisher;
	private String authors;
	private String genres;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Double getCost()
	{
		return cost;
	}

	public void setCost(Double cost)
	{
		this.cost = cost;
	}

	public boolean isAvailable()
	{
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable)
	{
		this.isAvailable = isAvailable;
	}

	public int getNoOfCopies()
	{
		return noOfCopies;
	}

	public void setNoOfCopies(int noOfCopies)
	{
		this.noOfCopies = noOfCopies;
	}

	public String getPublisher()
	{
		return publisher;
	}

	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getAuthors()
	{
		return authors;
	}

	public void setAuthors(String authors)
	{
		this.authors = authors;
	}

	public String getGenres()
	{
		return genres;
	}

	public void setGenres(String genres)
	{
		this.genres = genres;
	}

}
