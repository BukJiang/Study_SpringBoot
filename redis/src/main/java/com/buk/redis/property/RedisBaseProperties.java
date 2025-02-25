package com.buk.redis.property;

import lombok.Data;

import java.time.Duration;
import java.util.List;

/**
 * TODO: Redis属性基础配置
 *
 * @author BuK
 * @since 2020/08/22
 */
@Data
public class RedisBaseProperties {

    /**
     * Database index used by the connection factory.
     */
    private int database = 0;

    /**
     * Connection URL. Overrides host, port, and password. User is ignored. Example:
     * redis://user:password@example.com:6379
     */
    private String url;

    /**
     * Redis server host.
     */
    private String host = "localhost";

    /**
     * Login password of the redis server.
     */
    private String password;

    /**
     * Redis server port.
     */
    private int port = 6379;

    /**
     * Whether to enable SSL support.
     */
    private boolean ssl;

    /**
     * Connection timeout.
     */
    private Duration timeout;

    /**
     * Client name to be set on connections with CLIENT SETNAME.
     */
    private String clientName;

    private Sentinel sentinel;

    private Cluster cluster;

    private final Jedis jedis = new Jedis();

    private final Lettuce lettuce = new Lettuce();

    /**
     * Pool properties.
     */
    @Data
    public static class Pool {

        /**
         * Maximum number of "idle" connections in the pool. Use a negative value to
         * indicate an unlimited number of idle connections.
         */
        private int maxIdle = 8;

        /**
         * Target for the minimum number of idle connections to maintain in the pool. This
         * setting only has an effect if both it and time between eviction runs are
         * positive.
         */
        private int minIdle = 0;

        /**
         * Maximum number of connections that can be allocated by the pool at a given
         * time. Use a negative value for no limit.
         */
        private int maxActive = 8;

        /**
         * Maximum amount of time a connection allocation should block before throwing an
         * exception when the pool is exhausted. Use a negative value to block
         * indefinitely.
         */
        private Duration maxWait = Duration.ofMillis(-1);

        /**
         * Time between runs of the idle object evictor thread. When positive, the idle
         * object evictor thread starts, otherwise no idle object eviction is performed.
         */
        private Duration timeBetweenEvictionRuns;
    }

    /**
     * Cluster properties.
     */
    @Data
    public static class Cluster {

        /**
         * Comma-separated list of "host:port" pairs to bootstrap from. This represents an
         * "initial" list of cluster nodes and is required to have at least one entry.
         */
        private List<String> nodes;

        /**
         * Maximum number of redirects to follow when executing commands across the
         * cluster.
         */
        private Integer maxRedirects;
    }

    /**
     * Redis sentinel properties.
     */
    @Data
    public static class Sentinel {

        /**
         * Name of the Redis server.
         */
        private String master;

        /**
         * Comma-separated list of "host:port" pairs.
         */
        private List<String> nodes;

        /**
         * Password for authenticating with sentinel(s).
         */
        private String password;
    }

    /**
     * Jedis client properties.
     */
    @Data
    public static class Jedis {

        /**
         * Jedis pool configuration.
         */
        private Pool pool;
    }

    /**
     * Lettuce client properties.
     */
    @Data
    public static class Lettuce {

        /**
         * Shutdown timeout.
         */
        private Duration shutdownTimeout = Duration.ofMillis(100);

        /**
         * Lettuce pool configuration.
         */
        private Pool pool;

        private final Cluster cluster = new Cluster();

        @Data
        public static class Cluster {

            private final Refresh refresh = new Refresh();

            @Data
            public static class Refresh {

                /**
                 * Cluster topology refresh period.
                 */
                private Duration period;

                /**
                 * Whether adaptive topology refreshing using all available refresh
                 * triggers should be used.
                 */
                private boolean adaptive;
            }
        }
    }
}
