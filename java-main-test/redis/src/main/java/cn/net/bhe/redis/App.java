package cn.net.bhe.redis;

/**
 * ▪ redis是单线程执行，所以性能瓶颈不在CPU，在于内存大小、网络带宽、RTT(往返时间Round Trip Time)
 * ▪ 可以用pipeline来优化RTT问题，原理是将多个命令一次传输，一次返回，减少了TCP连接耗时
 */
public class App {
    
}
