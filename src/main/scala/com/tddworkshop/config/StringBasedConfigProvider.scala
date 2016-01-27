package com.tddworkshop.config

class StringBasedConfigProvider(source: String) extends ConfigProvider{

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
}

object ConfigProvider {
  def load(source: String) = new StringBasedConfigProvider(source)
}
