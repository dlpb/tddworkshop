package com.tddworkshop.config

protected class StringBasedConfigProvider(source: String) extends ConfigProvider{

  val properties: Map[String, String] = {
   source.trim.lines.filter{
     line => !line.trim.isEmpty
   }.map {
     line =>
       val parts = line.split("=", 2)
       (parts(0), parts(1))
   }.toMap
  }

  def getValue(s: String): Option[String] = properties.get(s)
}

trait ConfigProvider {
  def getValue(key: String): Option[String]
  def getValueWithFallback(key: String, fallbackValue: String): String = {
    getValue(key).getOrElse(fallbackValue)
  }
}

object ConfigProvider {
  def load(source: String) = new StringBasedConfigProvider(source)
}
