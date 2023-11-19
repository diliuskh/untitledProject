package net.dilius.untitled.lib.mongo

import com.mongodb.ConnectionString

private const val DEFAULT_HOST = "localhost"
private const val DEFAULT_PORT = 27017
private const val DEFAULT_DB = "test"
private const val DEFAULT_OPTS = ""

class ConnectionStringBuilder {

    private var username: String? = null
    private var password: String? = null
    private var host = DEFAULT_HOST
    private var port = DEFAULT_PORT
    private var database: String = DEFAULT_DB
    private var options = DEFAULT_OPTS

    fun withUsername(username: String?): ConnectionStringBuilder {
        username?.let { this.username = it }
        return this
    }

    fun withPassword(password: String?): ConnectionStringBuilder {
        password?.let {  this.password = it }
        return this
    }

    fun withHost(host: String): ConnectionStringBuilder {
        this.host = host
        return this
    }

    fun withPort(port: Int): ConnectionStringBuilder {
        this.port = port
        return this
    }

    fun withDatabase(database: String): ConnectionStringBuilder {
        this.database = database
        return this
    }

    fun withOptions(options: String): ConnectionStringBuilder {
        this.options = options
        return this
    }

    fun withOption(key: String, value: String): ConnectionStringBuilder {
        this.options += "&$key=$value"
        return this
    }

    fun withOption(key: String, value: Number): ConnectionStringBuilder {
        this.options += "&$key=$value"
        return this
    }

    fun build(): ConnectionString {
        val connectionString = StringBuilder("mongodb://")
        if (username != null && password != null) {
            connectionString.append(username).append(":").append(password).append("@")
        }
        connectionString.append(host).append(":").append(port)
        connectionString.append("/").append(database)
        if (options.isNotEmpty()) {
            connectionString.append("?").append(options.removePrefix("&"))
        }
        return ConnectionString(connectionString.toString())
    }
}