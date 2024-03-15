package com.everis.schedule.portlet;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEntryImpl;
import com.liferay.portal.kernel.scheduler.SchedulerException;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.TriggerFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.everis.messages.service.builder.service.MessagesLocalServiceUtil;
import com.everis.rec.rfilogs.model.RfiLogs;
import com.everis.rec.rfilogs.service.RfiLogsLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

@Component(immediate = true, property = { "cron.expression= 0 0 8 ? * MON,TUE,WED,THU,FRI *" // scheduler runs everyday
		// at 9 UK
}, service = RfilogSchedule.class)

public class RfilogSchedule extends BaseMessageListener {

	private static final Log logger = LogFactoryUtil.getLog(RfilogSchedule.class);
	private volatile boolean _initialized = false;

	@Override
	protected void doReceive(Message message) throws Exception {
		logger.info("RFI Log scheduled executed...");

		List<RfiLogs> rfilogsOpen = RfiLogsLocalServiceUtil.findByStatus("Open");
		List<RfiLogs> rfilogsPending = RfiLogsLocalServiceUtil.findByStatus("Pending");

		List<RfiLogs> rfilogs = new ArrayList<RfiLogs>();
		rfilogs.addAll(rfilogsOpen);
		rfilogs.addAll(rfilogsPending);
		logger.debug("RFILogs iteration...");
		// iterate rfilogs open and pending to check due dates
		for (RfiLogs rfilog : rfilogs) {
			Date rfiDate = rfilog.getDueDateFormated();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

			Date date = new Date();
			int businessDays = 10;
			Date nowPlus10 = addBussinessDays(date, businessDays);

			logger.debug("DueDate" + rfiDate);
			logger.debug("nowPlus10" + nowPlus10);
			
			if (fmt.format(rfiDate).equals(fmt.format(nowPlus10))) {
				
				logger.debug("Due date encountered in 10 days...");
				try {
					logger.debug("Sending notification 61...");
					MessagesLocalServiceUtil.sendNotification61(rfilog.getOrgSiteId(), rfilog.getTitle(),
							rfilog.getCompanyId());
				} catch (Exception e) {
					logger.error("Error sending notification 61 " + e);
				}
			}
			// check if is weekend
			Calendar c = Calendar.getInstance();
			int dow = c.get(Calendar.DAY_OF_WEEK);
			boolean isWeekday = ((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY));

			if (isWeekday) {
				logger.debug("Today is a week day...");
				if ((dow == Calendar.MONDAY)) {

					logger.debug("Today is monday...");
					// is monday so check if was friday the due date
					c.add(Calendar.DATE, -3);
					Date nowMinus3 = c.getTime();
					if (fmt.format(rfiDate).equals(fmt.format(nowMinus3))) {
						logger.debug("Due date encountered...");
						try {
							logger.debug("Sending notification 60...");
							MessagesLocalServiceUtil.sendNotification60(rfilog.getOrgSiteId(), rfilog.getTitle(),
									rfilog.getCompanyId());
						} catch (Exception e) {
							logger.error("Error sending notification 60 " + e);
						}
					}
				} else {

					logger.debug("Today isn't monday...");
					// tuesday-friday checking the day before
					c.add(Calendar.DATE, -1);

					Date nowMinus1 = c.getTime();

					if (fmt.format(rfiDate).equals(fmt.format(nowMinus1))) {
						logger.debug("Due date encountered...");
						try {
							logger.debug("Sending notification 60...");
							MessagesLocalServiceUtil.sendNotification60(rfilog.getOrgSiteId(), rfilog.getTitle(),
									rfilog.getCompanyId());
						} catch (Exception e) {
							logger.error("Error sending notification 60 " + e);
						}

					}
				}

			}
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws SchedulerException {

		try {
			// extract the cron expression from the properties
			String cronExpression = GetterUtil.getString(properties.get("cron.expression"), "cronExpression");
			logger.debug(" cronExpression: " + cronExpression);

			// create a new trigger definition for the job.
			String listenerClass = getClass().getName();
			Trigger jobTrigger = TriggerFactoryUtil.createTrigger(listenerClass, listenerClass, new Date(), null,
					cronExpression);
			// wrap the current scheduler entry in our new wrapper.
			SchedulerEntryImpl _schedulerEntryImpl = new SchedulerEntryImpl(listenerClass, jobTrigger);

			// if we were initialized (i.e. if this is called due to CA modification)
			if (_initialized) { // first deactivate the current job before we schedule.
				deactive();
			}

			// register the scheduled task
			SchedulerEngineHelperUtil.register(this, _schedulerEntryImpl, DestinationNames.SCHEDULER_DISPATCH);

			// set the initialized flag.
			// _initialized = true;

		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Deactivate
	protected void deactive() {
		SchedulerEngineHelperUtil.unregister(this);
		// clear the initialized flag
		_initialized = false;
	}

	private static final List<Integer> NON_BUSINESS_DAYS = Arrays.asList(Calendar.SATURDAY, Calendar.SUNDAY);

	private static Date addBussinessDays(Date date, int businessDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (int i = 0; i < Math.abs(businessDays);) {
			// here, all days are added/subtracted
			calendar.add(Calendar.DAY_OF_MONTH, businessDays > 0 ? 1 : -1);

			// but at the end it goes to the correct week day.
			// because i is only increased if it is a week day
			if (!NON_BUSINESS_DAYS.contains(calendar.get(Calendar.DAY_OF_WEEK))) {
				i++;
			}
		}
		return calendar.getTime();
	}

}
