package com.tddworkshop.config

class Config {
  def valueOf(s: String) = s match {
    case "present_value" => "present"
    case "value_1" => "value1"
    case "value_2" => "value2"
  }
}
