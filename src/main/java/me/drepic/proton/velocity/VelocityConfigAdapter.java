package me.drepic.proton.velocity;

import com.google.common.io.ByteStreams;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import me.drepic.proton.common.adapters.ConfigAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class VelocityConfigAdapter implements ConfigAdapter {

    private final VelocityBootstrap plugin;
    private YamlDocument config;

    public VelocityConfigAdapter(VelocityBootstrap plugin) {
        this.plugin = plugin;
    }

    @Override
    public void saveDefault() {}

    private void update(String newResource, File oldResource) throws IOException {}

    @Override
    public void loadConfig() {
        try {
            this.config = YamlDocument.create(new File(plugin.dataFolder(), "config.yml"), VelocityBootstrap.class.getClassLoader().getResourceAsStream("config.yml"),
                    GeneralSettings.builder().setUseDefaults(false).build(),
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.builder().setEncoding(DumperSettings.Encoding.UNICODE).build());
            plugin.logger().info(" | Config file loaded successfully: " + config.getFile().getAbsolutePath());
        } catch (Exception | Error e) {
            plugin.logger().severe("Failed to load config file.");
            e.printStackTrace();
        }
    }

    @Override
    public String getString(String path) {
        return this.config.getString(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return this.config.getStringList(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return this.config.getBoolean(path);
    }

    @Override
    public int getInt(String path) {
        return this.config.getInt(path);
    }
}
