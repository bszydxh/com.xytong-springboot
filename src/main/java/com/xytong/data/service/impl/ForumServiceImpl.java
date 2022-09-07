package com.xytong.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xytong.data.domain.Forum;
import com.xytong.data.mapper.ForumMapper;
import com.xytong.data.service.ForumService;
import org.springframework.stereotype.Service;

/**
* @author bszydxh
* @description 针对表【forum】的数据库操作Service实现
* @createDate 2022-09-03 01:13:25
*/
@Service
public class ForumServiceImpl extends ServiceImpl<ForumMapper, Forum>
implements ForumService {

}
