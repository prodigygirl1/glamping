package ru.promo.service.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.promo.service.BookingService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingScheduler {
    private final BookingService bookingService;

    @Scheduled(cron = "@hourly")
    public void cancelBooking() {
        var list = bookingService.cancelOlderThanTimeBookings(1L);
        if (log.isDebugEnabled()) {
            log.debug("Found '{}' expired booking requests", list);
        }
    }
}
