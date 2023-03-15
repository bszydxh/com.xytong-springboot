package com.xytong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.factory.PO2BOFactory;
import com.xytong.model.bo.ShBO;
import com.xytong.model.bo.UserBO;
import com.xytong.model.po.ShPO;
import com.xytong.mapper.ShMapper;
import com.xytong.service.ShService;
import com.xytong.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author bszydxh
 * @description 针对表【secondhand】的数据库操作Service实现
 * @createDate 2022-09-05 21:07:29
 */
@Service
public class ShServiceImpl extends ServiceImpl<ShMapper, ShPO>
        implements ShService {
    final UserService userService;

    public ShServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<ShBO> getShList(String mode, Long timestamp, int start, int end) {
        if (start > end) {
            throw new IllegalStateException("Unexpected value: " + start + end);
        }
        List<ShBO> shList = new ArrayList<>();
        switch (mode) {
            case "newest": {
                QueryWrapper<ShPO> wrapper = new QueryWrapper<>();
                Date date = new Date(timestamp);
                //过滤新数据
                wrapper.le("timestamp", date);
                wrapper.last("ORDER BY `id` DESC LIMIT " + start + "," + (end - start + 1));
                List<ShPO> shPOList = list(wrapper);
                for (ShPO shPO : shPOList) {
                    Long uid = shPO.getUserFkey();
                    shList.add(PO2BOFactory.getShBO(shPO, userService.findUserById(uid)));
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return shList;
    }

    @Override
    public boolean addSh(ShBO shBO) {
        if (shBO == null) {
            return false;
        }
        try {
            ShPO shPO = new ShPO();
            UserBO userBO = userService.findUserByName(shBO.getUserName());
            if (userBO == null) {
                log.error("not a valid user");
                return false;
            }
            shPO.setUserFkey(userBO.getId());
            shPO.setPrice(new BigDecimal(shBO.getPrice()));
            shPO.setImageUrl("");
            shPO.setTitle(shBO.getTitle());
            shPO.setText(shBO.getText());
            Date date = null;
            try {
                date = new Date(Long.parseLong(shBO.getTimestamp()));
                shPO.setTimestamp(date);
            } catch (Exception e) {
                e.printStackTrace();
                log.warn("not a valid date");
            }
            save(shPO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("save error");
            return false;
        }
        return true;
    }


    @Override
    public boolean checkCid(Long cid) {
        QueryWrapper<ShPO> shPOQueryWrapper = new QueryWrapper<>();
        shPOQueryWrapper.eq("id", cid);
        ShPO shPO = getOne(shPOQueryWrapper);
        if (shPO == null) {
            log.error("not a valid sh id");
            return false;
        }
        return true;
    }
}
