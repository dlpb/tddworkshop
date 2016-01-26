package com.tddworkshop.config

class Config {
  def valueOf(s: String): Option[String] = s match {
    case "present_value" => Some("present")
    case "value_1" => Some("value1")
    case "value_2" => Some("value2")
    case _ => None
  }
}
