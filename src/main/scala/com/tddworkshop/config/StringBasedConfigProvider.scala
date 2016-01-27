package com.tddworkshop.config

import java.net.URL

import scala.io.Source

protected class StringBasedConfigProvider private[config] (source: String) extends FallbackAvailableConfigProvider{

  private var fallback: Option[ConfigProvider] = None

  val properties: Map[String, String] = {
    source.trim.lines.filter{
     line => !line.trim.isEmpty
   }.map {
     line =>
     val parts = line.split("=", 2)
     (parts(0).trim, parts(1).trim)
   }.toMap
  }

  override def getValue(key: String): Option[String] =
    if(properties.contains(key)) properties.get(key)
    else {
      fallback flatMap {
        f => f.getValue(key)
      }
    }

  override def withFallback(fallbackConfigProvider: ConfigProvider): ConfigProvider = {
    fallback = Some(fallbackConfigProvider)
    this
  }

  override def toString() = {
    s"${properties.toString()} + ${this.hashCode()}"
  }
}

sealed trait ConfigProvider {
  def getValue(key: String): Option[String]

  def getValueWithFallback(key: String, fallbackValue: String): String = {
    getValue(key).getOrElse(fallbackValue)
  }

}

protected[config] trait FallbackAvailableConfigProvider extends ConfigProvider {
  def withFallback(fallbackConfigProvider: ConfigProvider): ConfigProvider
}


object ConfigProvider {
  def load(source: String): FallbackAvailableConfigProvider = new StringBasedConfigProvider(source)
  def load(url: URL): FallbackAvailableConfigProvider = new StringBasedConfigProvider(Source.fromURL(url).mkString)
}
