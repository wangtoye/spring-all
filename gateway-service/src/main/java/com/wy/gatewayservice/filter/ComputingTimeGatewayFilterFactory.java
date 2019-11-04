package com.wy.gatewayservice.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import reactor.core.publisher.Mono;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wangye
 * @date: 2019-07-02
 * Description:
 */
public class ComputingTimeGatewayFilterFactory extends AbstractGatewayFilterFactory implements Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ComputingTimeGatewayFilterFactory.class);
    private static final String COUNT_START_TIME = "countStartTime";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            exchange.getAttributes().put(COUNT_START_TIME, System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        Long startTime = exchange.getAttribute(COUNT_START_TIME);
                        if (startTime != null) {
                            logger.info("花费时间：" + exchange.getRequest().getURI().getRawPath() + ": "
                                    + (System.currentTimeMillis() - startTime) + "ms");
                        }
                    })
            );
        };
    }

    /**
     * 优先执行，然后回调最后执行，可以有效统计时间
     *
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
