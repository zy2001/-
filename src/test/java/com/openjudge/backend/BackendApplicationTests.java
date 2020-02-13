package com.openjudge.backend;

import com.openjudge.backend.Service.SendJudgeItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private SendJudgeItemService sendJudgeItemService;

    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("rid", 18);
        map.put("pid", 1);
        map.put("language", "C++");
        map.put("code", "#include<bits/stdc++.h>\n" +
                "//拉拉啊啦啦\n" +
                "using namespace std;\n" +
                "\n" +
                "int main我去(\")\n" +
                "{\n" +
                "\twhile(1){}\n" +
                "return 0;\n" +
                "}");
        map.put("timeLimit", 1000);
        map.put("memoryLimit", 64 * 1024);
        map.put("caseCount", 2);
        for (int i = 0; i < 1; i++)
            sendJudgeItemService.send(map);

    }

}
