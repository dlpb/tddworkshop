package com.tddworkshop.config

class Config(source: String) {

  val properties: Map[String, String] = {
   source.trim.lines.filter{
     line => !line.trim.isEmpty
   }.map {
     line =>
       val parts = line.split("=")
       println(s"$line -> parts are")
       parts foreach println
       (parts(0), parts(1))
   }.toMap
  }

  def this() = {
    this(
      """present_value=present
        |value_1=value1
        |value_2=value2""".stripMargin)
  }

  def valueOf(s: String): Option[String] = properties.get(s)
}
