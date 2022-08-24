package com.jbk.Service;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.jbk.Model.User;

@Service
public class UserDaoService {

	public static int userCount = 5;
	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "amar", new Date()));
		users.add(new User(2, "amar2", new Date()));
		users.add(new User(3, "amar3", new Date()));
		users.add(new User(4, "amar4", new Date()));
		users.add(new User(5, "amar5", new Date()));

	}

	public List<User> findAll() {
		return users;
	}

	public User findOne(@PathVariable int id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(userCount++);
			user.setName(user.getName());
			user.setDob(user.getDob());
		}
		users.add(user);
		return user;
	}

	public User deleteById(int id) {

		Iterator<User> iterator = users.iterator();

		while (iterator.hasNext()) {

			User u = iterator.next();

			if (u.getId() == id) {
				iterator.remove();
				return u;
			}
		}
		return null;

	}

}
