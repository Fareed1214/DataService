package com.dataservice.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.dataservice.bo.BookInfo;
import com.dataservice.bo.LibraryView;
import com.dataservice.util.MongoDBUtil;

@Repository
public class LibraryRepositoryImpl implements LibraryRepository
{

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.port}")
	private String port;

	@Value("${mongo.dbname}")
	private String dbName;

	@Value("${mongo.BookCollection}")
	private String bookColl;

	MongoTemplate mongoDBtemplate;;

	private MongoTemplate getTemplate()
	{
		if (mongoDBtemplate == null)
			mongoDBtemplate = MongoDBUtil.getMongoTemplate(host, Integer.valueOf(port), dbName);
		return mongoDBtemplate;
	}

	@Override
	public Boolean addBook(BookInfo info)
	{
		Boolean result = false;
		try {
			int copies = info.getNoOfCopies();
			List<BookInfo> list = fetchBookInfo(info.getTitle());
			if (list.size() > 0) {
				info = list.get(0);
				info.setNoOfCopies(info.getNoOfCopies() + copies);
			}
			info.setAvailable(info.getNoOfCopies() > 0);
			getTemplate().save(info);
			result = true;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public Boolean removeBook(String input)
	{
		Boolean result = false;
		try {
			List<BookInfo> list = fetchBookInfo(input);
			if (list.size() > 0) {
				BookInfo info = list.get(0);
				MongoTemplate template = getTemplate();
				if (info.getNoOfCopies() > 1) {
					info.setNoOfCopies(info.getNoOfCopies() - 1);
					template.save(info);
				} else
					template.remove(info);
				result = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public Boolean isAvailable(String input)
	{
		Boolean isAvailable = false;
		try {
			List<BookInfo> list = fetchBookInfo(input);
			isAvailable =  (list.size() > 0 && list.get(0).getNoOfCopies() >= 1);
		} catch (Exception e) {
			throw e;
		}
		return isAvailable;
	}

	@Override
	public BookInfo searchBook(String input)
	{
		BookInfo info = null;
		try {
			List<BookInfo> list = fetchBookInfo(input);
			if (list.size() > 0)
				info = list.get(0);
		} catch (Exception e) {
			throw e;
		}
		return info;
	}

	@Override
	public LibraryView searchBooks()
	{
		LibraryView view = null;
		try {
			MongoTemplate template = getTemplate();
			List<BookInfo> bookInfos = template.find(new Query(), BookInfo.class);
			if (bookInfos.size() > 0) {
				view = new LibraryView();
				bookInfos.stream().forEach(info -> info.setAvailable(info.getNoOfCopies() > 0));
				view.setLibrary(bookInfos);
			}
		} catch (Exception e) {
			throw e;
		}
		return view;
	}

	private List<BookInfo> fetchBookInfo(String input)
	{
		List<BookInfo> bookInfos;
		try {
			MongoTemplate template = getTemplate();
			Query query = new Query();
			if ((input.length() == 24 && (input.matches("\\b[0-9A-Fa-f]+\\b"))))
				query.addCriteria(Criteria.where("_id").is(new ObjectId(input)));
			else
				query.addCriteria(Criteria.where("title").is(input));
			bookInfos = template.find(query, BookInfo.class);
		} catch (Exception e) {
			throw e;
		}
		return bookInfos;
	}

}
