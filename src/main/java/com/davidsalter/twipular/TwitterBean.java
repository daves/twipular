/*
 * Twipular is a sample Java EE 6 application that queries Twitter to find the most popular clients.
 */

package com.davidsalter.twipular;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/**
 * Java EE managed bean which manages the lifecycle of the application and
 * provides a list of used Twitter clients and the date of last refresh to the
 * web tier. In addition, the bean allows the list of Twitter clients to be
 * updated.
 * 
 * The bean is annotated with @Named so that it is accessible from the web page
 * via EL.
 * 
 * The bean is annotated with @ApplicationScoped so that it has application
 * scope.
 * 
 * @author David
 * 
 */
@ApplicationScoped
@Named
public class TwitterBean {

	/**
	 * The TwitterClientsManager instance.
	 */
	private TwitterClientsManager manager = new TwitterClientsManager();

	/**
	 * The date the list of used clients was last refreshed.
	 */
	private Date lastUpdated = null;

	/**
	 * Gets a list of used Twitter clients for display in the web tier.
	 * 
	 * @return List of Twitter clients.
	 */
	public List<TwitterClient> getTwitterClients() {

		lastUpdated = new Date();

		Twitter twitter = new TwitterFactory().getInstance();
		try {
			ResponseList<Status> publicTimeline = twitter.getPublicTimeline();
			for (Status status : publicTimeline) {
				manager.add(status);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return manager.getTwitterClients();
	}

	/**
	 * Gets the date and time the list of Twitter clients was last updated.
	 * 
	 * @return Last updated time.
	 */
	public Date getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * Refreshes the page and therefore the list of Twitter clients.
	 * 
	 * @return Returns an empty string to redirect to the same page that invoked
	 *         this method.
	 */
	public String refresh() {
		return "";
	}
}
