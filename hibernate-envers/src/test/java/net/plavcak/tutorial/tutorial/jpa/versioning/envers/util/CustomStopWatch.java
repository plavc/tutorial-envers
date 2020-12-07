package net.plavcak.tutorial.tutorial.jpa.versioning.envers.util;

import java.text.NumberFormat;
import org.springframework.util.StopWatch;

public class CustomStopWatch extends StopWatch {

    @Override
    public String prettyPrint() {
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append('\n');

        sb.append("---------------------------------------------\n");
        sb.append("ns         %     Task name\n");
        sb.append("---------------------------------------------\n");
        NumberFormat nf = NumberFormat.getNumberInstance();
        //nf.setMinimumIntegerDigits(9);
        nf.setGroupingUsed(false);
        NumberFormat pf = NumberFormat.getPercentInstance();
        pf.setMinimumIntegerDigits(3);
        pf.setGroupingUsed(false);
        for (TaskInfo task : getTaskInfo()) {
            sb.append(nf.format(task.getTimeSeconds())).append("  ");
            sb.append(pf.format((double) task.getTimeNanos() / getTotalTimeNanos())).append("  ");
            sb.append(task.getTaskName()).append("\n");
        }

        return sb.toString();
    }
}
