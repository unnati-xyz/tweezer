package xyz.unnati.harate

import scala.collection.JavaConversions._
import grizzled.slf4j.Logging
import services.TwitterStreamListener
import twitter4j.{FilterQuery, TwitterStreamFactory}

object Harate extends Logging with App {

  override
  def main(args: Array[String]): Unit = {

    val conf = ConfigHelper.conf

    info("Setting up twiiter client config")
    val twitterConfig = new twitter4j.conf.ConfigurationBuilder()
      .setOAuthConsumerKey(conf.getString("twitter.oAuthConsumerKey"))
      .setOAuthConsumerSecret(conf.getString("twitter.oAuthConsumerSecret"))
      .setOAuthAccessToken(conf.getString("twitter.oAuthAccessToken"))
      .setOAuthAccessTokenSecret(conf.getString("twitter.oAuthAccessTokenSecret"))
      .setJSONStoreEnabled(true)
      .build()

    val twitterStream = new TwitterStreamFactory(twitterConfig).getInstance

    val tracks = conf.getStringList("twitter.tracks")
    twitterStream.addListener(TwitterStreamListener.simpleStatusListener)
    val query = new FilterQuery()
    for (track <- tracks) {
      println(track)
      query.track(track)
    }

    twitterStream.filter(query)

  }

}
