package com.fantastictrio.cw4sem

import com.fantastictrio.cw4sem.model.Decision
import com.fantastictrio.cw4sem.model.DecisionRecord

object DecisionMaker {
    /**
     * matrix has rows (outer list) and columns (inner list), it's a matrix of variables needed for making the decision
     * excluding strategy list names and nature states numbers. E.g.
     *  nature state-->   1  2  3
     *                    --------
     *  bankrupcy       | 15 22 70 |
     *  run away        | 44 11 03 |
     *  refuse proposal | 87 22 41 | <-- matrix
     *                    --------
     * **/
    @JvmStatic
    fun make(decision: Decision, matrix: List<List<Double>>): DecisionRecord {
        return DecisionRecord(0,0,0,decision)
        //TODO: Add algorithm for making decisions
    }
}
