package navigationtiming;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Performance {

    private String performanceJavaScript = "var performance = window.performance || {};var timings = performance.timing || {};return timings;";

    private WebDriver driver;


    InfluxDB influxDB = InfluxDBFactory.connect("http://localhost:8086/");

    BatchPoints batchPoints = BatchPoints.database("clientside").retentionPolicy("autogen").build();

    public boolean checkDBConnection() {
        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Pong response = influxDB.ping();
        return !response.getVersion().equalsIgnoreCase("unknown");
    }

    public Performance(WebDriver driver) {
        this.driver = driver;
    }

    public Map<String, Object> getTimings() {
        return (Map<String, Object>) ((JavascriptExecutor) driver).executeScript(performanceJavaScript);
    }

    public void writeToInflux(String page) {
        getTimings();

        influxDB.setLogLevel(InfluxDB.LogLevel.BASIC);
        Point point = Point.measurement("clientside")

                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("projectName", "demo.nopcommerce")
                .tag("scenario", "Performance Test")
                .tag("browser", "Chrome")
                .tag("page", page)
                .addField("latency", getLatency())
                .addField("backend_response", getBackendResponse())
                .addField("tti", getTimeToInteract())
                .addField("ttl",getTimeToLoad())
                .addField("on_load", getOnLoad())
                .addField("total_time", getTotalTime())
                .build();

        batchPoints.point(point);
        influxDB.write(batchPoints);
    }


    private long getTiming(String name) {
        return (long) getTimings().get(name);
    }

    public long getNavigationStart() {
        return getTiming("navigationStart");
    }

    public long getResponseStart() {
        return getTiming("responseStart");
    }

    public long getResponseEnd() {
        return getTiming("responseEnd");
    }

    public long getDomLoading() {
        return getTiming("domLoading");
    }

    public long getDomInteractive() {
        return getTiming("domInteractive");
    }

    public long getDomComplete () {
        return getTiming("domComplete");
    }

    public long getLoadEventStart() {
        return getTiming("loadEventStart");
    }

    public long getLoadEventEnd() {
        return getTiming("loadEventEnd");
    }



    public long getLatency(){
        return getResponseEnd() - getNavigationStart();
    }

    public long getBackendResponse() {
        return getResponseEnd() - getResponseStart();
    }

    public long getTimeToInteract() {
        return getDomInteractive() - getDomLoading();
    }

    public long getTimeToLoad() {
        return getDomComplete() - getDomInteractive();
    }

    public long getOnLoad() {
        return getLoadEventEnd() - getLoadEventStart();
    }

    public long getTotalTime() {
        return getLoadEventEnd() - getNavigationStart();
    }
}
