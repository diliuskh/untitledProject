package net.dilius.untitled.lib.mongo

import com.mongodb.*
import com.mongodb.connection.ClusterSettings
import com.mongodb.connection.ConnectionPoolSettings
import com.mongodb.connection.SocketSettings
import com.mongodb.connection.TransportSettings
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.netty.buffer.ByteBufAllocator
import io.netty.channel.EventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.handler.ssl.SslContext
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.configuration.CodecRegistry
import org.bson.codecs.pojo.PojoCodecProvider
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import java.util.concurrent.TimeUnit


@Configuration
class MongoDbClientConfiguration {

    @Bean
    fun mongoClient(springContext: ApplicationContext): MongoClient {
        val pojoCodecRegistry: CodecRegistry = CodecRegistries.fromRegistries(
            MongoClientSettings.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build())
        )

        // Connection String
        val connectionString: ConnectionString =
            ConnectionString("srv+mongodb://user:password@host:27017/dbname?retryWrites=true&w=majority")


        // Connection Pool Settings
        val connectionPoolSettings = ConnectionPoolSettings.builder()
            .maxSize(20) // Maximum number of connections in the pool
            .minSize(10) // Minimum number of connections in the pool
            .maxConnectionIdleTime(60, TimeUnit.SECONDS) // Maximum idle time for a pooled connection
            .maxConnectionLifeTime(120, TimeUnit.SECONDS) // Maximum lifetime for a pooled connection
            .build()

        // Socket Settings

        // Socket Settings
        val socketSettings = SocketSettings.builder()
            .connectTimeout(10, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Read timeout
            .build()

        // Cluster Settings

        // Cluster Settings
        val clusterSettings = ClusterSettings.builder()
            .hosts(Arrays.asList(ServerAddress("host", 27017)))
            .requiredReplicaSetName("replicaSetName") // For replica sets
            .build()

        // Build MongoClientSettings

        // Build MongoClientSettings
        val settings: MongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(pojoCodecRegistry)
            .transportSettings(TransportSettings.nettyBuilder().build())
            .applyToConnectionPoolSettings { builder: ConnectionPoolSettings.Builder ->
                builder.applySettings(connectionPoolSettings)
            }
            .applyToSocketSettings { builder: SocketSettings.Builder -> builder.applySettings(socketSettings) }
            .applyToClusterSettings { builder: ClusterSettings.Builder -> builder.applySettings(clusterSettings) }
            .readConcern(ReadConcern.MAJORITY)
            .writeConcern(WriteConcern.MAJORITY)
            .build()
        return MongoClient.create(settings)
    }

    @Bean
    fun transportSettings(
        eventLoopGroup: EventLoopGroup,
        socketChannelClass: Class<out SocketChannel>
    ): TransportSettings {
        return TransportSettings.nettyBuilder()
            .allocator(ByteBufAllocator.DEFAULT)
            .eventLoopGroup(eventLoopGroup)
            .socketChannelClass(socketChannelClass)
            .build()
    }
}