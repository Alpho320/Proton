package me.drepic.proton.bukkit;

import com.github.Anon8281.universalScheduler.UniversalScheduler;
import com.github.Anon8281.universalScheduler.scheduling.schedulers.TaskScheduler;
import me.drepic.proton.common.adapters.SchedulerAdapter;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitSchedulerAdapter implements SchedulerAdapter {

    private JavaPlugin plugin;
    private TaskScheduler taskScheduler;

    protected BukkitSchedulerAdapter(JavaPlugin plugin) {
        this.plugin = plugin;
        this.taskScheduler = UniversalScheduler.getScheduler(plugin);
    }

    @Override
    public void runTask(Runnable runnable) {
        this.taskScheduler.runTask(runnable);
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        this.taskScheduler.runTaskAsynchronously(runnable);
    }
}
