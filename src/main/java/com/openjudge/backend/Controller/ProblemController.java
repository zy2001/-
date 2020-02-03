package com.openjudge.backend.Controller;

import com.openjudge.backend.DTO.ProblemDetailDTO;
import com.openjudge.backend.DTO.ResponseResult;
import com.openjudge.backend.DTO.SampleDTO;
import com.openjudge.backend.Domain.Problem;
import com.openjudge.backend.Domain.Sample;
import com.openjudge.backend.Mapper.ProblemMapper;
import com.openjudge.backend.Mapper.SampleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by zy on 2020/1/28
 */

@CrossOrigin
@RestController
public class ProblemController {

    @Autowired
    private ProblemMapper problemMapper;

    @Autowired
    private SampleMapper sampleMapper;

    @GetMapping("/problem/{pid}")
    public ResponseResult getProblemDetail(@PathVariable(name = "pid") Integer pid) {
        //构造各类对象
        ResponseResult result = new ResponseResult();               //返回结果
        try {
            ProblemDetailDTO problemDetailDTO = new ProblemDetailDTO(); //题目信息
            List<SampleDTO> sampleDTOList = new ArrayList<>();          //样例
            //获取题目信息
            Problem problem = problemMapper.selectProblemByPid(pid);
            BeanUtils.copyProperties(problem, problemDetailDTO);
            //获取示例信息
            List<Sample> sampleList = sampleMapper.selectSampleByPid(pid);
            for (Sample sample : sampleList) {
                SampleDTO sampleDTO = new SampleDTO();
                BeanUtils.copyProperties(sample, sampleDTO);
                sampleDTOList.add(sampleDTO);
            }
            problemDetailDTO.setSamples(sampleDTOList);
            result.setData(problemDetailDTO);
            result.setSuccess(true);
            result.setMessage("题目信息获取成功");
            result.setErrorcode("200");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
