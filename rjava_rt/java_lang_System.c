#include "java_lang_System.h"
#include "boehm-gc/include/gc.h"
#include <stdio.h>
#include <time.h>
#include <sys/time.h>

#ifdef __OS_MACOSX_
#include <mach/clock.h>
#include <mach/mach.h>
#endif

void java_lang_System_gc() {
#ifdef GC_THREADS
    GC_gcollect();
#endif
}

extern void rjava_debug_report_func_log();
void java_lang_System_exit_int32_t(int32_t exit_code) {
#ifdef LOG_FUNCTION_EXEC
    rjava_debug_report_func_log();
#endif
    exit(exit_code);
}

int64_t java_lang_System_currentTimeMillis() {
    struct timespec ts;
    
#ifdef __OS_MACOSX_ // OS X does not have clock_gettime, use clock_get_time
    clock_serv_t cclock;
    mach_timespec_t mts;
    host_get_clock_service(mach_host_self(), CALENDAR_CLOCK, &cclock);
    clock_get_time(cclock, &mts);
    mach_port_deallocate(mach_task_self(), cclock);
    ts.tv_sec = mts.tv_sec;
    ts.tv_nsec = mts.tv_nsec;
    
#else
    clock_gettime(CLOCK_REALTIME, &ts);
#endif

    return (int64_t) (ts.tv_sec * 1000 + ts.tv_nsec / 1000000);
}

int64_t java_lang_System_nanoTime() {
    struct timespec ts;
    
#ifdef __OS_MACOSX_ // OS X does not have clock_gettime, use clock_get_time
    clock_serv_t cclock;
    mach_timespec_t mts;
    host_get_clock_service(mach_host_self(), CALENDAR_CLOCK, &cclock);
    clock_get_time(cclock, &mts);
    mach_port_deallocate(mach_task_self(), cclock);
    ts.tv_sec = mts.tv_sec;
    ts.tv_nsec = mts.tv_nsec;
    
#else
    clock_gettime(CLOCK_REALTIME, &ts);
#endif
    
    return (int64_t) ts.tv_sec * 1000000000LL + (int64_t)ts.tv_nsec;
}