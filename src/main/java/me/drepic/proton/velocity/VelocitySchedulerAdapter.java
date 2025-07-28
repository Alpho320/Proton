package me.drepic.proton.velocity;

import me.drepic.proton.common.adapters.SchedulerAdapter;

public class VelocitySchedulerAdapter implements SchedulerAdapter {

    private final VelocityBootstrap plugin;

    public VelocitySchedulerAdapter(VelocityBootstrap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void runTask(Runnable runnable) {
        this.plugin.proxy().getScheduler().buildTask(plugin, runnable).schedule();
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        this.plugin.proxy().getScheduler().buildTask(plugin, runnable).schedule();
    }

}
