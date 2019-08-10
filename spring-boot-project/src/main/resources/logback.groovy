import ch.qos.logback.classic.filter.ThresholdFilter

def appenderList = []
def LOG_DIR = "/opt/logs"
def isConsolePrint = true

def env = System.getProperty("spring.profiles.active")

if ("pro" == env) {
    appenderList.add("ROLLING")
    isConsolePrint = false
} else {
    appenderList.add("CONSOLE")
}

if (isConsolePrint) {
    appender("CONSOLE", ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        }
    }
} else {
    appender("ROLLING", RollingFileAppender) {
        file = "error.log"
        filter(ThresholdFilter) {
            // 级别在 warn 以上才记录
            level = WARN
        }
        encoder(PatternLayoutEncoder) {
            Pattern = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
        }
        rollingPolicy(TimeBasedRollingPolicy) {
            // 一周压缩一次
            FileNamePattern = "${LOG_DIR}/app.%d{yyyy-ww}.gz"
        }
    }
}

root(INFO, appenderList)

scan("30 seconds")