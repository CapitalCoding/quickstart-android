package br.com.diogomoreno.events.domain.model

class CheckIn {
    var eventId: String? = null
    var name: String? = null
    var email: String? = null

    constructor(eventId: String?, name: String?, email: String?) {
        this.eventId = eventId
        this.name = name
        this.email = email
    }

    constructor() {}
}