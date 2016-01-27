package com.tddworkshop.config

class Config(source: String) {

  val properties: Map[String, String] = {
   source.trim.lines.filter{
     line => !line.trim.isEmpty
   }.map {
     line =>
       val parts = line.split("=", 2)
       (parts(0), parts(1))
   }.toMap
  }

  def valueOf(s: String): Option[String] = properties.get(s)
}
