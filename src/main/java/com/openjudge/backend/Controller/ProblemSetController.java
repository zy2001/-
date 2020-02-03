package com.openjudge.backend.Controller;

import com.openjudge.backend.DTO.ProblemItemDTO;
import com.openjudge.backend.DTO.ProblemSetDTO;
import com.openjudge.backend.DTO.ResponseResult;
import com.openjudge.backend.Domain.Problem;
import com.openjudge.backend.Mapper.ProblemMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zy on 2020/1/22
 */

@CrossOrigin
@RestController
public class ProblemSetController {

    @Autowired
    private ProblemMapper problemMapper;

    @PostMapping("/problemset")
    public ResponseResult ProblemSet(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "size", defaultValue = "20") Integer size) {
        ResponseResult result = new ResponseResult();
        try {
            //获取分页内题目
            List<Problem> problemList = problemMapper.selectProblemItemByPage((page - 1) * size, size);
            List<ProblemItemDTO> problemItemDTOList = new ArrayList<>();
            //精简题目信息
            for (Problem problem : problemList) {
                ProblemItemDTO problemItemDTO = new ProblemItemDTO();
                BeanUtils.copyProperties(problem, problemItemDTO);
                problemItemDTOList.add(problemItemDTO);
            }
            //填充题目集
            ProblemSetDTO problemSetDTO = new ProblemSetDTO();
            problemSetDTO.setItemList(problemItemDTOList);
            problemSetDTO.setSize(size);
            //获取题目总数
            Integer total = problemMapper.selectCount();
            problemSetDTO.setTotal(total);

            //填充返回数据结构
            result.setErrorcode("200");
            result.setMessage("请求成功");
            result.setSuccess(true);
            result.setData(problemSetDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
