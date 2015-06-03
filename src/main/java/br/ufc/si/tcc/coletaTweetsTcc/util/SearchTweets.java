package br.ufc.si.tcc.coletaTweetsTcc.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import twitter4j.HashtagEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import br.ufc.si.tcc.coletaTweetsTcc.model.Search;
import br.ufc.si.tcc.coletaTweetsTcc.model.Tweet;
import br.ufc.si.tcc.coletaTweetsTcc.model.UserTwitter;

public class SearchTweets implements Runnable{

	private Search search;
	private Twitter twitter;
	
	public SearchTweets(Search search, Twitter twitter){
		this.search = search;
		this.twitter = twitter;
	}

	@Override
	public void run() {
		
		int count = 1;
		long lastSearch = 0;
		
		while (count <= search.getNumberSearch()) {
			
			Query query = new Query();
			query.setQuery(search.getValueSearch());
			query.setLang("pt");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			query.setSince(sdf.format(search.getDateCreation()));
			
			QueryResult searchResult = null;
			
			try {
				
				searchResult = twitter.search(query);
				
				CSVUtil csv = new CSVUtil();
				csv.createFile(search.getDateCreation(), count);
				
				int qtd = 0;
				
				while (searchResult.hasNext()) {
					
					query = searchResult.nextQuery();
					
					if (count != 1)
						query.setSinceId(lastSearch);
					
					searchResult = twitter.search(query);
					
					List<Status> statusList = searchResult.getTweets();
					
					qtd += statusList.size();
					
					Set<User> users = new LinkedHashSet<User>();
					List<Tweet> tweets = new ArrayList<Tweet>();
					
					for (Status status : statusList){
						
						csv.addLine(status, search.getDateCreation(), count);
						User user = status.getUser();
						
						UserTwitter userTwitter = new UserTwitter();
						userTwitter.setId(user.getId());
						userTwitter.setLocation(user.getLocation());
						userTwitter.setName(user.getName());
						userTwitter.setUserName(user.getScreenName());
						
						users.add(user);
						
						Tweet tweet = new Tweet();
						tweet.setUser(userTwitter);
						tweet.setSearch(search);
						tweet.setText(status.getText());
						tweet.setDate(status.getCreatedAt());
						
						try {
							tweet.setLatitude(status.getGeoLocation()
									.getLatitude());
						} catch (Exception e) {
						}
						try {
							tweet.setLongitude(status.getGeoLocation()
									.getLongitude());
						} catch (Exception e) {
						}
						
						HashtagEntity hashtags[] = status.getHashtagEntities();
						List<String> hashtagsList = new ArrayList<String>();
						for (int i = 0; i < hashtags.length; i++) {
							hashtagsList.add(hashtags[i].getText());
						}
						tweet.setHashTags(hashtagsList);
						
						UserMentionEntity mentioned[] = status
								.getUserMentionEntities();
						List<String> mentionedList = new ArrayList<String>();
						for (int i = 0; i < mentioned.length; i++) {
							mentionedList.add(mentioned[i].getText());
						}
						tweet.setMentioned(mentionedList);
						
						tweets.add(tweet);
						
					}
					
					if (qtd >= search.getQuantity()) break;
						
				}
				
				Thread.currentThread();
				Thread.sleep(search.getTimeSearch());
				
			} catch (Exception e) {
				
				e.printStackTrace();
				continue;
				
			}
			
			count++;
			
		}
		
	}
	
}
