package cn.ldbz.admin.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ldbz.pojo.LdbzResult;

@ControllerAdvice
public class ExceptionHandle {
	
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public LdbzResult handler( Exception e){
        if(StringUtils.contains(e.getMessage(), "org.springframework.dao.DuplicateKeyException")){
            return LdbzResult.build(500, "操作失败，主键冲突。","记录重复，添加失败。");
        }else {
            logger.info("[系统异常] {}",e);
            return LdbzResult.build(500, "操作失败，未知错误。");
        }
    }
}