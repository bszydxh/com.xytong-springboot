package com.xytong.task;

import com.xytong.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class LikeTask extends QuartzJobBean {
    final LikeService likeService;

    public LikeTask(LikeService likeService) {
        this.likeService = likeService;
    }

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(@NotNull JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("likeTask-------- {}", sdf.format(new Date()));
        //将 Redis 里的点赞信息同步到数据库里
        likeService.transLikedFromRedis2DB();
    }

}
