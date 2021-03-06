Qlog Plugin
===========

Add default production logging setup with Graylog2, including the request URL on each applicable log record.

Configuration
-------------

No configuration should be necessary in the default case, but the following can be configured in Config.groovy:

```groovy
graylog2 {
	server = 'xyz.com' // Graylog2 server, defaults to logging.quirkagency.com (production), logging.quirkstaging.com (staging)
	facility = 'abc.com' //defaults to app name
	threshold  = org.apache.log4j.Level.DEBUG // defaults to org.apache.log4j.Level.INFO
	conversionPattern: '%d{dd-MM-yyyy HH:mm:ss,SSS} %5p %c{1} - %m%n' // defaults to '%d %-5p [%c] (%t) %X{requestURL} %m%n' 
}
```
