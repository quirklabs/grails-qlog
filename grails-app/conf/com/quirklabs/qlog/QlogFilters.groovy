package com.quirklabs.qlog

class QlogFilters {
    def filters = {
        all(controller:'*', action:'*') {
            before = {
                MDC.put("requestURL", "${request.forwardURI}${request.queryString ? '?' + request.queryString : ''}")
            }
        }
    }
}