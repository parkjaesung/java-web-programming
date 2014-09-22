package next.service;

import java.util.List;

import next.dao.LogInfoDao;
import next.model.LogInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
	private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);
	
	@Autowired
	private LogInfoDao logInfoDao;
	
	public void create(LogInfo logInfo) {
		logInfoDao.create(logInfo);
		
		List<LogInfo> infos = logInfoDao.findAll();
		for (LogInfo each : infos) {
			logger.debug(each.toString());
		}
	}
}
