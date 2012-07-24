package com.quirklabs.qlog

import org.apache.log4j.MDC

class QlogFilters {
    def filters = {
        all(controller:'*', action:'*') {
            before = {
                MDC.put("requestURL", "${request.forwardURI}${request.queryString ? '?' + request.queryString : ''}")
            }
        }
    }
}