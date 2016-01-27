package com.tddworkshop.config

protected class StringBasedConfigProvider private[config] (source: String) extends ConfigProvider{

  private var fallback: Option[ConfigProvider] = None

  val properties: Map[String, String] = {
    source.trim.lines.filter{
     line => !line.trim.isEmpty
   }.map {
     line =>
     val parts = line.split("=", 2)
     (parts(0).trim, parts(1).trim)
   }.toMap
  }

  override def getValue(key: String): Option[String] =
    if(properties.contains(key)) properties.get(key)
    else {
      fallback flatMap {
        f => f.getValue(key)
      }
    }

  override def withFallback(fallbackConfigProvider: ConfigProvider): ConfigProvider = {
    fallback = Some(fallbackConfigProvider)
    this
  }

  override def toString() = {
    s"${properties.toString()} + ${this.hashCode()}"
  }
}

trait ConfigProvider {

  def getValue(key: String): Option[String]

  def getValueWithFallback(key: String, fallbackValue: String): String = {
    getValue(key).getOrElse(fallbackValue)
  }

  def withFallback(fallbackConfigProvider: ConfigProvider): ConfigProvider

}

object ConfigProvider {
  def load(source: String): ConfigProvider = new StringBasedConfigProvider(source)
}
