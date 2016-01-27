package com.tddworkshop.config

import org.scalatest.{Matchers, FlatSpec}

class ServiceConfigTest extends FlatSpec with Matchers {

  "The config loader" should "give a value when geting a property whose value is present" in {
    val config = new Config()
    config.valueOf("present_value") should be(Some("present"))
  }

  it should "give a value for two properties when getting those properties and their values are present" in {
    val config = new Config()
    config.valueOf("value_1") should be(Some("value1"))
    config.valueOf("value_2") should be(Some("value2"))
  }

  it should "give a none when the property cannot be found" in {
    val config = new Config()
    config.valueOf("rubbish") should be(None)
  }

  it should "read in some properties from a string when created" in {
    val configString = "source=string"
    val config = new Config(configString)
    config.valueOf("source") should be(Some("string"))
  }

  it should "read in some properties from a string, when there is leading and trailing whitespace" in {
    val configString =
      """
        |source=string
      """.stripMargin

    val config = new Config(configString)
    config.valueOf("source") should be(Some("string"))
  }


}
