package com.tddworkshop.config

import org.scalatest.{Matchers, FlatSpec}

class ServiceConfigTest extends FlatSpec with Matchers {

  "The config loader" should "give a value when geting a property whose value is present" in {
    val config = new Config()
    config.valueOf("present_value") should be("present")
  }

  it should "give a value for two properties when getting those properties and their values are present" in {
    val config = new Config()
    config.valueOf("value_1") should be("value1")
    config.valueOf("value_2") should be("value2")
  }


}
