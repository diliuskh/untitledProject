package net.dilius.untitled.lib.mongo

import com.mongodb.MongoClientSettings
import com.mongodb.connection.NettyTransportSettings
import com.mongodb.connection.TransportSettings
import com.mongodb.reactivestreams.client.MongoClient
import io.netty.channel.EventLoopGroup
import io.netty.channel.socket.SocketChannel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn

@Configuration
@DependsOn("nettyConfiguration")
class MongoConfiguration {

    @Bean
    fun nettyTransportSettings(
        @Qualifier("clientEventLoopGroup") eventLoopGroup: EventLoopGroup,
        @Qualifier("clientSocketChannel") socketChannel: Class<out SocketChannel>
    ): NettyTransportSettings {
        return NettyTransportSettings
            .nettyBuilder()
            .eventLoopGroup(eventLoopGroup)
            .socketChannelClass(socketChannel)
            .build()
    }

    @Bean
    fun mongoClient(@Value("\${mongodb.uri}") uri: String, transportSettings: TransportSettings): MongoClient {
        MongoClientSettings.builder()
            .applyConnectionString(com.mongodb.ConnectionString(uri))
            .transportSettings(transportSettings)
            .build()
            .let { settings ->
                return com.mongodb.reactivestreams.client.MongoClients.create(settings)
            }
    }
}