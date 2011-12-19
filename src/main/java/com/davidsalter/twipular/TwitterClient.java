/*
 * Twipular is a sample Java EE 6 application that queries Twitter to find the most popular clients.
 */

package com.davidsalter.twipular;

/**
 * Class representing a Twitter client. A twitter client has a name and a count
 * of the number of times it has been used in the recent timeline.
 * 
 * Example Twitter clients include, "Web", "Twitter for BlackBerry",
 * "Twitter for Android" etc.
 * 
 * @author David
 * 
 */
public class TwitterClient {

	private String name;
	private int count;

	public TwitterClient(String name, int count) {
		this.name = name;
		this.setCount(count);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
