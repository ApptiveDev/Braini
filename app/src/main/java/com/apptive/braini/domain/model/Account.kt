package com.apptive.braini.domain.model

/** 로그인한 유저를 표현하는 클래스 */
class Account(
    val token: String = "",
    val id: String,
    val email: String,
    val name: String,
) {
    fun isGuest() : Boolean = (this == GUEST)

    override fun toString(): String {
        return "User(id: $id, email: $email, name: $name, token: $token"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Account) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        var result = token.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    companion object {
        val GUEST = Account("", "", "", "Guest")
    }
}