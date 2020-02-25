package com.openjudge.backend.Service;

import com.alibaba.fastjson.JSONObject;
import com.openjudge.backend.Config.RabbitMQConfig;
import com.openjudge.backend.Mapper.ProblemMapper;
import com.openjudge.backend.Mapper.SubmitMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by zy on 2020/2/11
 */

@Service
public class UpdateJudgeStatusService {

    @Autowired
    private SubmitMapper submitMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @RabbitListener(queues = RabbitMQConfig.JUDGE_STATUS_QUEUE_NAME)
    public void comsumeMessage(String msg) {
        Map params = JSONObject.parseObject(msg, Map.class);
        System.out.println(params);
        submitMapper.updateStatusByRid(params);
        if (null != params.get("status") && params.get("status").equals(11)) {
            problemMapper.updateAcceptByPid(params.get("pid"));
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
