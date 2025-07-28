package me.drepic.proton.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.drepic.proton.common.Proton;
import me.drepic.proton.common.ProtonBootstraper;
import me.drepic.proton.common.adapters.ConfigAdapter;
import me.drepic.proton.common.adapters.SchedulerAdapter;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(
        id = "proton",
        name = "proton",
        version = "1.0.0",

        authors = {"Drepic", "Alpho320"}
)
public class VelocityBootstrap implements ProtonBootstraper {

    private final ProxyServer proxyServer;
    private final Logger logger;

    private final File dataFolder;

    private VelocitySchedulerAdapter schedulerAdapter;
    private VelocityConfigAdapter configAdapter;
    private Proton proton;

    @Inject
    public VelocityBootstrap(ProxyServer proxyServer, Logger logger, @DataDirectory Path dataDirectory) {
        this.proxyServer = proxyServer;
        this.logger = logger;
        this.dataFolder = dataDirectory.toFile();
    }

    @Subscribe
    public void onInit(ProxyInitializeEvent event) {
        this.schedulerAdapter = new VelocitySchedulerAdapter(this);
        this.configAdapter = new VelocityConfigAdapter(this);
        this.proton = new Proton(this);
        this.proton.enable();
    }

    @Subscribe
    public void onShutdown(ProxyShutdownEvent event) {
        disable();
    }

    public ProxyServer proxy() {
        return this.proxyServer;
    }

    public Logger logger() {
        return this.logger;
    }

    public File dataFolder() {
        return this.dataFolder;
    }

    @Override
    public Logger getPluginLogger() {
        return this.logger;
    }

    @Override
    public SchedulerAdapter getScheduler() {
        return this.schedulerAdapter;
    }

    @Override
    public ConfigAdapter getConfiguration() {
        return this.configAdapter;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public void disable() {
        logger().severe("Disabling Proton...");
        if (this.proton != null) {
            this.proton.disable();
        }
    }

}