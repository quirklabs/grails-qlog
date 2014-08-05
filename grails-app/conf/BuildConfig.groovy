grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()        
        grailsCentral()
        mavenRepo "http://artifactory.quirk.biz/artifactory/ext-snapshot-local/"
    }
    dependencies {
        compile 'org.graylog2:gelfj:0.9.1-SNAPSHOT'
        runtime 'com.googlecode.json-simple:json-simple:1.1.1'
    }

    plugins {
        build(":release:3.0.1") {
            export = false
        }
    }
}

grails.project.repos.default = "quirkRepo"
grails.release.scm.enabled = false