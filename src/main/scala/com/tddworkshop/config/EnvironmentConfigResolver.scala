package com.tddworkshop.config

object EnvironmentConfigResolver {
  def load(environment: String, filename: String = "application.properties"): ConfigProvider =
    ConfigProvider.load(getClass.getResource(s"/$environment/$filename"))
}
