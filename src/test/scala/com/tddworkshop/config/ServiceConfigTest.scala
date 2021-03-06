package com.tddworkshop.config

import org.scalatest.{Matchers, FlatSpec}

class ServiceConfigTest extends FlatSpec with Matchers {

  "The config loader" should "give a value when geting a property whose value is present" in {
    val config = ConfigProvider.load("present_value=present")
    config.getValue("present_value") should be(Some("present"))
  }

  it should "give a value for two properties when getting those properties and their values are present" in {
    val config = new StringBasedConfigProvider(
                                                """
                                                  |value_1=value1
                                                  |value_2=value2
                                                """.stripMargin)
    config.getValue("value_1") should be(Some("value1"))
    config.getValue("value_2") should be(Some("value2"))
    config.getValue("present_value") should be(None)
  }

  it should "give a none when the property cannot be found" in {
    val config = ConfigProvider.load("")
    config.getValue("rubbish") should be(None)
  }

  it should "read in some properties from a string when created" in {
    val configString = "source=string"
    val config = ConfigProvider.load(configString)
    config.getValue("source") should be(Some("string"))
  }

  it should "read in some properties from a string, when there is leading and trailing whitespace" in {
    val configString =
      """
        |source=string
      """.stripMargin

    val config = ConfigProvider.load(configString)
    config.getValue("source") should be(Some("string"))
  }

  it should "read in properites when there is whitespace between lines" in {
    val configString =
      """
        |source=string
        |
        |type=test
      """.stripMargin

    val config = ConfigProvider.load(configString)
    config.getValue("source") should be(Some("string"))
    config.getValue("type") should be(Some("test"))
  }

  it should "support property values with an equals sign in them" in {
    val configString =
      """
        |data=value=true
      """.stripMargin

    val config = ConfigProvider.load(configString)
    config.getValue("data") should be(Some("value=true"))
  }

  it should "trim whitespace around the = character" in {
    val configString =
      """
        |data =    value
      """.stripMargin

    val config = ConfigProvider.load(configString)
    config.getValue("data") should be(Some("value"))
  }

  it should "allow us to provide a fallback for a specific key if it doesnt exist" in {
    val config = ConfigProvider.load("")
       config.getValueWithFallback("rubbish", "fallback") should be("fallback")
  }

  it should "allow us to compose with a fallback config in case the main config doesnt provide the right key" in {
    val config = ConfigProvider.load("").withFallback(ConfigProvider.load("fallback=true"))
    config.getValue("fallback") should be(Some("true"))
  }


  it should "read in a config from a properties file" in {
    val config = ConfigProvider.load(getClass.getResource("/example.properties"))
    config.getValue("source") should be(Some("file"))
  }

}
