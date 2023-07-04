package com.github.xcykrix.plugincommon.api;

import com.github.xcykrix.plugincommon.PluginCommon;
import com.github.xcykrix.plugincommon.api.helper.configuration.LanguageFile;
import com.github.xcykrix.plugincommon.api.records.Resource;
import com.github.xcykrix.plugincommon.extendables.SimpleAPI;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import dev.dejvokep.boostedyaml.spigot.SpigotSerializer;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class ConfigurationAPI extends SimpleAPI {
    private final HashMap<String, YamlDocument> yamlDocumentHashMap = new HashMap<>();
    private LanguageFile languageFile;

    /**
     * Creates an Access API for the BoostedYaml API.
     *
     * @param pluginCommon PluginCommon API.
     *
     *                     <br></br>
     *                     <a href="https://github.com/dejvokep/boosted-yaml">Access the BoostedYaml Documentation</a>.
     */
    public ConfigurationAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
    }

    /**
     * Register a Resource to the Configuration API.
     *
     * @param resource The Resource.
     * @return {@link ConfigurationAPI}
     */
    public ConfigurationAPI register(Resource resource) {
        try {
            yamlDocumentHashMap.put(resource.id(), YamlDocument.create(
                    resource.parentFolder() == null ? new File(this.pluginCommon.getDataFolder(), resource.id()) : new File(this.pluginCommon.getDataFolder() + "/" + resource.parentFolder(), resource.id()),
                    resource.resource(),
                    GeneralSettings.builder().setSerializer(SpigotSerializer.getInstance()).setUseDefaults(true).build(),
                    LoaderSettings.builder().setAutoUpdate(true).build(),
                    DumperSettings.DEFAULT,
                    UpdaterSettings.builder().setVersioning(new BasicVersioning("version")).build()
            ));
        } catch (IOException exception) {
            this.pluginCommon.getLogger().severe("Failed to Initialize Configuration File: " + resource.id());
            this.pluginCommon.getLogger().severe(ExceptionUtils.getStackTrace(exception));
        }
        return this;
    }

    public ConfigurationAPI registerLanguageFile(InputStream inputStream) {
        this.register(new Resource("language.yml", null, inputStream));
        return this;
    }

    /**
     * Access the Configuration YamlDocument at the specified Resource ID.
     *
     * @param id The Resource ID.
     * @return {@link YamlDocument}
     */
    public YamlDocument get(String id) {
        return this.yamlDocumentHashMap.get(id);
    }

    public LanguageFile getLanguageFile() {
        if (this.languageFile == null) {
            YamlDocument yamlDocument = this.get("language.yml");
            if (yamlDocument == null)
                throw new IllegalStateException("Unable to access LanguageFile. LanguageFile is not initialized.");
            this.languageFile = new LanguageFile(this.pluginCommon, this.get("language.yml"));
        }
        return this.languageFile;
    }
}
