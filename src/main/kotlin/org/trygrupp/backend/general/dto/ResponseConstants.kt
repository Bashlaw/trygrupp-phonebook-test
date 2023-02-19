package org.trygrupp.backend.general.dto

interface ResponseConstants {

    interface ResponseCode {
        companion object {
            const val SUCCESS = "00"
            const val FAILED = "96"
        }
    }

    interface ResponseMessage {
        companion object {
            const val SUCCESS = "SUCCESS"
            const val FAILED = "FAILED"
        }
    }

}