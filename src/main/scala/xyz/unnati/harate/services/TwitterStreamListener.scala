package xyz.unnati.harate.services

import twitter4j.{StallWarning, Status, StatusDeletionNotice, StatusListener, TwitterObjectFactory}
import com.mongodb.{MongoClient, MongoClientURI}
import com.mongodb.client.MongoCollection
import com.typesafe.config.Config
import grizzled.slf4j.Logging
import org.bson.Document
import xyz.unnati.harate.ConfigHelper


object TwitterStreamListener extends Logging {

  val collection = getMongoCollection()

  def getMongoCollection(): MongoCollection[Document] = {
    val config = ConfigHelper.conf
    val mongoClient = initializeMongoClient(config)
    val db = mongoClient.getDatabase(config.getString("mongo.dbname"))
    db.getCollection(config.getString("mongo.collection"))
  }

  def simpleStatusListener = new StatusListener() {

    def onStatus(status: Status) {

      val timeStamp = status.getCreatedAt.getTime
      val tweetJson = TwitterObjectFactory.getRawJSON(status)
      val doc = Document.parse(tweetJson)
      doc.put("timestamp_ms", timeStamp)
      collection.insertOne(doc)
    }

    def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
    def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
    def onException(ex: Exception) {
      error(ex.printStackTrace())
    }
    def onScrubGeo(arg0: Long, arg1: Long) {}
    def onStallWarning(warning: StallWarning) {}
  }


  def initializeMongoClient(config: Config): MongoClient = {
    val host = config.getString("mongo.host")
    val port = config.getInt("mongo.port")
    val user = config.getString("mongo.username")
    val pass = config.getString("mongo.password")
    val dbname = config.getString("mongo.dbname")
    val doAuth = config.getBoolean("mongo.authenticate")

    val builder = StringBuilder.newBuilder

    var uri = ""
    if (doAuth) {
      uri = builder.append("mongodb://")
        .append(user).append(":").append(pass).append("@")
        .append(host).append(":").append(port).append("/").append(dbname).toString()

    } else {
      uri = builder.append("mongodb://")
        .append(host).append(":").append(port).append("/").append(dbname).toString()
    }

   info(uri)
    new MongoClient(new MongoClientURI(uri))
  }

}
