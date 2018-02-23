package com.journaldev.spring.http.client;

import java.util.List;

public interface RestClient<T> {
	final String HOST = "https://jsonplaceholder.typicode.com";

	final String ALBUMS_URL = HOST + "/albums";

	final String PHOTOS_URL = HOST+"/photos";

	final String USER_URL = HOST+"/users";

	public List<T> getList();

}
