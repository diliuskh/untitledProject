package net.dilius.untitled.lib.netty

import io.netty.channel.EventLoopGroup
import io.netty.channel.epoll.Epoll
import io.netty.channel.epoll.EpollEventLoopGroup
import io.netty.channel.epoll.EpollSocketChannel
import io.netty.channel.kqueue.KQueue
import io.netty.channel.kqueue.KQueueEventLoopGroup
import io.netty.channel.kqueue.KQueueSocketChannel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NettyConfiguration {
    @Bean("clientEventLoopGroup")
    fun eventLoopGroup(): EventLoopGroup {
        val numberOfThreads = Runtime.getRuntime().availableProcessors()

        return if (Epoll.isAvailable()) {
            EpollEventLoopGroup(numberOfThreads)
        } else if (KQueue.isAvailable()) {
            KQueueEventLoopGroup(numberOfThreads)
        } else {
            NioEventLoopGroup(numberOfThreads)
        }
    }

    @Bean("clientSocketChannel")
    fun socketChannelClass(): Class<out SocketChannel> {
        return if (Epoll.isAvailable()) {
            EpollSocketChannel::class.java
        } else if (KQueue.isAvailable()) {
            KQueueSocketChannel::class.java
        } else {
            NioSocketChannel::class.java
        }
    }
}
