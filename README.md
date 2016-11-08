# Tweezer

> The ultimate twitter streaming data collector

At [Unnati](http://unnati.xyz) we're a bunch of Data Scientists solving
important business problems.

The crux of solving these data related problems is being able to collect the
data itself. In some cases, data might already be available in the way we want
it, but in most cases, it has to either be procured or transformed to fit our
needs.

Social media analytics is turning out to be a very important aspect of a
business. End users are quick to praise and even quicker to shame a brand or a
product on Social Media. This has resulted in a rapid investment being put into
being able to monitor and act on inputs received from Social Media.

But to begin, we need data.

## The Problem

Consider Twitter. Their RESTful APIs are quite stringent in terms of Rate
limits. What we really want to use is their streaming API. The streaming API
doesn't have rate limits and grants us the power of processing these things in
near real time.

The problem is in reinventing the wheel, most of the times, we end up writing
the data collection layer time and again with minor changes to the codebase.
The crux of the collection layer though, largely remains the same.

## The Solution

To solve this problem, we built _Tweezer_. With Tweezer, you can start
collecting data in **under 5 mins**. All you need is a [twitter authorized
app](https://dev.twitter.com/oauth/overview) created at your end and an
instance of MongoDB.

We have a handy configuration file to manage the workings of the app. This
configuration file will have the authorized app credentials, data store
credentials and the keywords/hashtags to track.

---

Very recently, [HasGeek](https://hasgeek.com/) conducted their annual
conference on JavaScript in India - [JsFoo](http://jsfoo.in/). To test run
Tweezer, we left it running for 3 days monitoring the hashtags and keywords
relevant to the event.

Using this data, we have even put together a dashboard visualizing the various
angles of JsFoo: [here](http://jsfoo2016.unnati.xyz)

## Usage

### Using Docker

#### Build

Use the `Dockerfile` and build the docker image

The docker image comes with `jdk-8` and `mongodb`

```
$ sudo docker build -t mytwitterstream .
```

Once the image is built, make sure you add the credentials in `application.conf`

- specify the twitter API credentials
- specify mongo db credentials

#### Run

Run the docker image.

```
$ sudo docker run -t -i mytwitterstream
```

This internally starts mongodb, starts the twitter streamer app which writes
tweets to the local db.

### Using the source

#### Build

```
$ sbt build
```

Set the required credentials in `application.conf`

#### Run

```
$ sbt run
```

### Using the JAR directly

If you have Java 7+ and mongodb 3 installed and do not want the docker setup,
you can pickup the jar from [dropbox](https://www.dropbox.com/s/gau7688ftw18xci/tweezer-0.2.0.jar?dl=1) 
and run tweezer. Make sure you configure the `application.conf` (here is a [sample](https://github.com/unnati-xyz/tweezer/blob/master/application.conf))
and set an environment variable `HARATE_CONF` pointing to the location of the
configuration file

```
export HARATE_CONF=/path/to/application.conf
```

Once we have the path configured, we are ready to run the jar

```
$ java -jar tweezer-0.2.0.jar
```

