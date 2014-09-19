package next.service;

import next.dao.LogInfoDao;
import next.model.LogInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
	@Autowired
	private LogInfoDao logInfoDao;
	
	public void create(LogInfo logInfo) {
		logInfoDao.create(logInfo);
	}
}
