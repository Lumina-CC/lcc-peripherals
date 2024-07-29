package io.sc3.peripherals.config;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;

public final class ScPeripheralsConfig {
  @NotNull
  public static final ScPeripheralsConfig INSTANCE = new ScPeripheralsConfig();
  @NotNull
  private static ConfigSpec spec = new ConfigSpec();
  private static final CommentedFileConfig config;
  @NotNull
  public ConfigSpec getSpec() {
    return spec;
  }

  public static CommentedFileConfig getConfig() {
    return config;
  }

   static {
    spec.define("printer.print_ticks", 100);

    spec.define("printer.chamelium_value", 2000);
    spec.define("printer.ink_value", 50000);

    spec.define("printer.max_base_light_level", 7);
    spec.define("printer.max_shapes", 128);

    spec.define("printer.custom_redstone_cost", 300);
    spec.define("printer.noclip_cost_multiplier", 2);
    spec.define("printer.recycle_value_multiplier", 0.75);

    spec.define("poster_printer.print_ticks", 100);

    spec.define("poster_printer.ink_cost", 5000);

    spec.define("prometheus.enabled", false);
    spec.define("prometheus.port", 9090);

    var dir = FabricLoader.getInstance().getConfigDir().resolve("luminacc");
    dir.toFile().mkdirs();
    var conf = CommentedFileConfig
      .builder(dir.resolve("lcc-peripherals.toml"))
      .defaultResource("/data/lcc-peripherals/default-config.toml")
      .autosave()
      .build();

    conf.load();
    spec.correct(conf);
    conf.save();
    config = conf;
  }
}
