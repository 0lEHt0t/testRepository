package scheduler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import servlet.Common;

public class SchedulerManager {
	private ScheduledExecutorService scheduler;
	private static SchedulerManager instance;

	private SchedulerManager() {
		scheduler = Executors.newScheduledThreadPool(1);
	}

	public static SchedulerManager getInstance() {
		if (instance == null) {
			instance = new SchedulerManager();
		}
		return instance;
	}

	public void startScheduler() {
		System.out.println("스케줄러가 시작되었음");
		scheduler.scheduleAtFixedRate(() -> {
			try {
				incrementMemberPoints();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 0, 20, TimeUnit.SECONDS);
	}

	public void stopScheduler() {
		System.out.println("스케줄러의 실행이 종료되었음");
		scheduler.shutdown();
	}

	private void incrementMemberPoints() throws Exception {
		try (Connection conn = Common.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE member SET point = point + 1")) {
			int updatedRows = pstmt.executeUpdate();

			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			String formattedTime = formatter.format(new Date());

			System.out.println("Job이 실행되어, " + updatedRows + "명에게 1포인트가 부여되었음 (" + formattedTime + ")");
		}
	}
}
