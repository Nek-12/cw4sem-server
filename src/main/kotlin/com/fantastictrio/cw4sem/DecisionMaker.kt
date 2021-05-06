package com.fantastictrio.cw4sem

import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.model.DecisionRecord

object DecisionMaker {
    @JvmStatic
    fun make(decision: Decision): DecisionRecord {
        return DecisionRecord(0,0,0,decision)
        //TODO: Add algorithm for making decisions
    }
}
