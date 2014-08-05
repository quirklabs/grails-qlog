import org.apache.log4j.Logger
import org.apache.log4j.PatternLayout
import org.apache.log4j.Appender
import org.apache.log4j.Level
import grails.util.Environment

class QlogGrailsPlugin {
    def version = "1.3"
    def grailsVersion = "2.0 > *"
    def dependsOn = [:]
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Qlog Plugin"
    def author = "Craig Raw"
    def authorEmail = "craig@quirk.biz"
    def description = '''\
Add default staging and production logging setup with Gelf protocol.
'''
    def documentation = "https://github.com/quirklabs/grails-qlog/blob/master/README.md"
    def license = "APACHE"
    def organization = [ name: "Quirk Labs", url: "http://www.quirklabs.com" ]
    def issueManagement = [ system: "github", url: "https://github.com/quirklabs/grails-qlog/issues" ]
    def scm = [ url: "https://github.com/quirklabs/grails-qlog" ]

    def doWithSpring = {
        def rL = Logger.rootLogger
        def layout = new PatternLayout(conversionPattern: application.config.graylog2.conversionPattern ?: '%d %-5p [%c] (%t) %X{requestURL} %m%n')
        def defaultGraylogHost = "localhost"
        if(Environment.current.name == 'staging') {
          defaultGraylogHost = "logging.quirkstaging.com"
        }
        if(Environment.current.name == 'production') {
          defaultGraylogHost = "logging.quirkagency.com"
        }
        
        def facility = (grails.util.Metadata.current.'app.name').toString()

        Appender appender = new org.graylog2.log.GelfAppender(
                name:"gelf",
                graylogHost: application.config.graylog2.server ?: defaultGraylogHost,
                extractStacktrace: true,
                addExtendedInformation: true,
                additionalFields: "{'runtime': 'grails', 'environment': '${Environment.current.name}', 'transport': 'gelf-java'}",
                facility: facility,
                threshold: application.config.graylog2.threshold ?: org.apache.log4j.Level.INFO,
                layout: layout
        )

        appender.activateOptions()
        rL.addAppender(appender)
    }
}
