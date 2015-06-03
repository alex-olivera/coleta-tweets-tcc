package br.ufc.si.tcc.coletaTweetsTcc.util;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class AutenticationFactory {
	
	public static Twitter getTwitter(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey(Constants.CONSUMERKEY)
		.setOAuthConsumerSecret(Constants.CONSUMERSECRET)
		.setOAuthAccessToken(Constants.ACCESSTOKEN)
		.setOAuthAccessTokenSecret(Constants.ACCESSTOKENSECRET);
		
		TwitterFactory twitterFactory = new TwitterFactory(cb.build());
		
		Twitter twitter = twitterFactory.getInstance();
		return twitter;
	}
	
}
