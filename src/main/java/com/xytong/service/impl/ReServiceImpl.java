package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.model.bo.ReBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.RePO;
import com.xytong.mapper.ReMapper;
import com.xytong.service.ReService;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【run_errand】的数据库操作Service实现
 * @createDate 2022-09-05 21:07:29
 */
@Service
public class ReServiceImpl extends ServiceImpl<ReMapper, RePO>
        implements ReService {
    final UserService userService;

    public ReServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<ReBO> getReList(String mode, Long timeStamp, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ReBO> reList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                QueryWrapper<RePO> wrapper = new QueryWrapper<>();
                Date date = new Date(timeStamp);
                //过滤新数据
                wrapper.le("timestamp", date);
                wrapper.last("ORDER BY `id` DESC LIMIT " + start + "," + (end - start + 1));
                List<RePO> rePOList = list(wrapper);
                for (RePO rePO : rePOList) {
                    Long uid = rePO.getUserFkey();
                    reList.add(PO2BOFactory.getReBO(rePO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return reList;
    }

    @Override
    public Boolean addRe(ReBO reBO) {
        if (reBO == null) {
            return false;
        }
        try {
            RePO rePO = new RePO();
            UserBO userBO = userService.findUserByName(reBO.getUserName());
            if (userBO == null) {
                log.error("not a valid user");
                return false;
            }
            rePO.setUserFkey(userBO.getId());
            rePO.setPrice(new BigDecimal(reBO.getPrice()));
            rePO.setTitle(reBO.getTitle());
            rePO.setText(reBO.getText());
            Date date = null;
            try {
                date = new Date(Long.parseLong(reBO.getTimestamp()));
                rePO.setTimestamp(date);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("not a valid date");
            }
            save(rePO);
        } catch (Exception e) {
            log.error("save error");
            return false;
        }
        return true;
    }
}
