package com.ecosnap.Model

import java.io.Serializable

class dbHistoryItem (
        val recyclable: Boolean = false,
        val confidence: Float = 0.00F,
        val datetime: String = "",
        val imgPath: String = ""
) : Serializable