package com.openjudge.backend.Service;

import com.alibaba.fastjson.JSON;
import com.openjudge.backend.Config.RabbitMQConfig;
import com.openjudge.backend.Mapper.ProblemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2020/2/11
 */

@Service
@EnableAsync
public class SendJudgeItemService {

    private final Logger logger = LoggerFactory.getLogger(SendJudgeItemService.class);

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    public void send(Map<String, Object> params) {
        Map<String, Object> map = problemMapper.selectRunLimit(params);
        for (String i : map.keySet()) {
            params.put(i, map.get(i));
        }
        logger.info(JSON.toJSONString(params));
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.JUDGE_ITEM_ROUTING_KEY, JSON.toJSONString(params));
    }

}
