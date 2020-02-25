package com.openjudge.backend.Controller;

import com.openjudge.backend.DTO.ResponseResult;
import com.openjudge.backend.DTO.StatusSetDTO;
import com.openjudge.backend.DTO.SubmitItemDTO;
import com.openjudge.backend.Mapper.ProblemMapper;
import com.openjudge.backend.Mapper.SubmitMapper;
import com.openjudge.backend.Service.SendJudgeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by zy on 2020/1/29
 */

@CrossOrigin
@RestController
public class SubmitController {

    @Autowired
    private SubmitMapper submitMapper;

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SendJudgeItemService sendJudgeItemService;

    @GetMapping("/send")
    public Object sendMessage() {
        Map<String, Object> map = submitMapper.selectSubmit();
        sendJudgeItemService.send(map);
        return "ok";
    }

    @PostMapping("/submit")
    public ResponseResult submit(@RequestParam Map<String, Object> params) {
        ResponseResult result = new ResponseResult();
        try {
            //判断来值是否完整
            if (null == params.get("pid"))
                throw new Exception();
            if (null == params.get("uid"))
                throw new Exception();
            if (null == params.get("language"))
                throw new Exception();
            if (null == params.get("code"))
                throw new Exception();
            //补充提交参数
            params.put("status", "0");
            Date date = new Date(System.currentTimeMillis());
            params.put("gmtCreated", date);
            params.put("gmtModified", date);
            //插入提交数据
            int cnt = submitMapper.insertSubmit(params);
            //插入成功返回成功信息
            if (cnt == 1) {
                problemMapper.updateTotalByPid(params.get("pid"));
                result.setMessage("提交成功");
                result.setSuccess(true);
                result.setErrorcode("200");
                //提交成功，发送信息给评测机
                sendJudgeItemService.send(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/submitStatus")
    public ResponseResult submitStatus(@RequestParam(value = "pid", required = false) Integer pid,
                                       @RequestParam("size") Integer size,
                                       @RequestParam("page") Integer page) {
        ResponseResult result = new ResponseResult();
        try {

            List<SubmitItemDTO> submitItemDTOList;
            StatusSetDTO statusSetDTO = new StatusSetDTO();
            if(null != pid) {
                submitItemDTOList = submitMapper.selectStatusByPId(pid, (page - 1) * size, size);
                statusSetDTO.setTotal(submitMapper.selectCountByPId(pid));
            }
            else {
                submitItemDTOList = submitMapper.selectStatus((page - 1) * size, size);
                statusSetDTO.setTotal(submitMapper.selectCount());
            }
            statusSetDTO.setItemList(submitItemDTOList);
            statusSetDTO.setSize(size);
            result.setErrorcode("200");
            result.setSuccess(true);
            result.setMessage("请求成功");
            result.setData(statusSetDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
