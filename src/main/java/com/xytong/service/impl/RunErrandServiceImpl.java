package com.xytong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.model.bo.ReBO;
import com.xytong.model.bo.ReBO;
import com.xytong.model.po.RePO;
import com.xytong.model.po.RePO;
import com.xytong.mapper.ReMapper;
import com.xytong.service.RunErrandService;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【run_errand】的数据库操作Service实现
 * @createDate 2022-09-05 21:07:29
 */
@Service
public class RunErrandServiceImpl extends ServiceImpl<ReMapper, RePO>
        implements RunErrandService {
    final UserService userService;

    public RunErrandServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public List<ReBO> getReList(String mode, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ReBO> reList= new ArrayList<>();
        switch (mode) {
            case "newest": {
                List<RePO> forumPOList = list();
                for (RePO rePO : forumPOList) {
                    int uid = rePO.getUserFkey();
                    reList.add(ReBO.init(rePO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return reList;
    }
}
