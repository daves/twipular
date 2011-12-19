/*
 * Twipular is a sample Java EE 6 application that queries Twitter to find the most popular clients.
 */

package com.davidsalter.twipular;

import java.util.ArrayList;
import java.util.HashMap;

import twitter4j.Status;

/**
 * Management facade class responsible for maintaining details of the Twitter
 * clients in use.
 * 
 * @author David
 * 
 */
public class TwitterClientsManager {

	/**
	 * Simple HashMap backing store used to maintain list of clients in use.
	 */
	private HashMap<String, Integer> clients = new HashMap<String, Integer>();

	/**
	 * Add a Twitter client to the list of used clients. Only the
	 * Status.getSource() field is stored.
	 * 
	 * @param status
	 *            Twitter status message.
	 */
	public void add(Status status) {
		// Get the client from the hash if it there and increase its count.
		// If it's not there, add it and set its count to 1.
		Integer count = clients.get(status.getSource());

		if (count == null) {
			clients.put(status.getSource(), new Integer(1));
		} else {
			Integer removed = clients.remove(status.getSource());
			int newCount = count + 1;
			clients.put(status.getSource(), new Integer(newCount));
		}
	}

	/**
	 * Returns a list of used Twitter clients. Each Twitter client contains a
	 * name and a count of the number of times it has been used.
	 * 
	 * @return List of Twitter Clients
	 * @see TwitterClient
	 */
	public ArrayList<TwitterClient> getTwitterClients() {
		ArrayList<TwitterClient> twitterClients = new ArrayList<TwitterClient>();
		for (String status : clients.keySet()) {
			Integer count = clients.get(status);
			twitterClients.add(new TwitterClient(status, count));
		}
		return twitterClients;
	}
}
