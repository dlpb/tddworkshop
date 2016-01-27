package com.tddworkshop.config

import org.scalatest.{Matchers, FlatSpec}

class EnvironmentConfigResolverTest extends FlatSpec with Matchers {

  "Environment resolver" should "load config for a specified environment from a folder of the same name" in {
    val config = EnvironmentConfigResolver.load("qa")
    config.getValue("source") should be(Some("file"))
    config.getValue("environment") should be(Some("qa"))
  }
}
