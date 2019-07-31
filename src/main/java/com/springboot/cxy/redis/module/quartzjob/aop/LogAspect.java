package com.springboot.cxy.redis.module.quartzjob.aop;

import com.springboot.cxy.redis.module.quartzjob.annotation.AddSysLog;
import com.springboot.cxy.redis.module.quartzjob.entity.SysLogEntity;
import com.springboot.cxy.redis.module.quartzjob.util.IpUtil;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 系统日志切面类
 */
@Aspect
@Component
public class LogAspect {
	
	private static final Logger logger = Logger.getLogger(LogAspect.class);
	
//	@Resource
//	private SysLogService sysLogService;
	
	
	 /**
	  * 标注该方法体为后置通知，当目标方法执行成功后执行该方法体     
	  * @param jp
	  * @param rl
	  */
    @AfterReturning("within(com.springboot.cxy.*.controller..*) && @annotation(rl)")
    public void addLogSuccess(JoinPoint jp, AddSysLog rl){
    	try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			String ip = IpUtil.getRemortIP(request);
			//从session获取用户信息
			//SysUser sysUser = (SysUser) request.getSession().getAttribute("SysUser");
			String uri = request.getRequestURI();
			String signature = jp.getSignature().toString();//获取目标方法签名     
			String methodName = signature.substring(signature.lastIndexOf(".")+1, signature.indexOf("("));
			SysLogEntity log = new SysLogEntity();
//			log.setAddUser(sysUser.getUserName());
			log.setContent(rl.desc());
			log.setCreateTime(new Date());
			log.setMethod(methodName);
			log.setRemoteAddr(ip);
			log.setRequestUri(uri);
//			sysLogService.insert(log);
		} catch (Exception e) {
			logger.error("保存系统日志出现异常", e);
		}
    }

}
