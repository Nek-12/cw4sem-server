package com.fantastictrio.cw4sem.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class IllegalDecisionException(s: String) : IllegalArgumentException(s)
