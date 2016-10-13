# Tweezer #

The idea behind this project is simple, if one has to do analytics on Twitter data, one has to start collecting the data.
We wanted to run some analytics for the [JSFoo 2016 event](http://jsfoo.in) conducted by [HasGeek](http://https://hasgeek.com/) and realized that it is tedious to write the boilerplate code everytime someone has to collect data.
So we put together a simple framework for anyone who has to collect streaming data from twitter, and get it running in under 5 minutes.

Streaming api of twitter has no rate limits, so its simpler to use it. All we need to start collecting data is to have a [twitter authorized app](https://dev.twitter.com/oauth/overview).
Connect to the streaming api with the authorized app credentials and you are ready to fetch tweets in realtime.

We have a [handy configuration file](https://gitlab.com/unnati/harate/blob/master/application.conf) designed to manage the authorized app credentials, data store credentials and the tracks to fetch data for (hashtags / keywords)

For the JSFoo event, we filtered the twitter stream for tags related to the event and ran the service for 3 days.

## Build & run jar using Docker ##

#### Build

Use the `Dockerfile` and build the docker image

The docker image comes with `jdk-8` and `mongodb`

```
$ sudo docker build -t mytwitterstream .
```

Once the image is built, make sure you add the credentials in `application.conf`

* specify the twitter API credentials
* specify mongo db credentials

#### Run

Run the docker image.

```
$ sudo docker run -t -i mytwitterstream
```

This internally starts mongodb, starts the twitter streamer app which writes tweets to the local db.

## Build & run from source ##

#### Build

```
$ sbt build
```

#### Run

```
$ sbt run
```
