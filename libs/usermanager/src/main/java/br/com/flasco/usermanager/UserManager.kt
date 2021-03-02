package br.com.flasco.usermanager

import br.com.flasco.cache.Cache

class UserManager(private val cache: Cache = Cache()) {

    var newUser: Boolean
        get() = cache.get<Boolean>(NEW_USER) ?: true
        set(value) = cache.set(NEW_USER, value)

    var name: String?
        get() = cache.get<String>(NAME)
        set(value) = cache.set(NAME, value)

    internal companion object {
        const val NEW_USER = "new_user"
        const val NAME = "name"
    }
}
