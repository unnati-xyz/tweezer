package xyz.unnati.harate

import java.io.File

import com.typesafe.config.{Config, ConfigFactory}
import grizzled.slf4j.Logging

object ConfigHelper extends Logging{

  val conf = this.load()

  def load(): Config = {
    info("Loading application configuration")
    val path = sys.env.get("HARATE_CONF").get
    info("Config file is here " + path)

    ConfigFactory.parseFile(new File(path))
  }

}
