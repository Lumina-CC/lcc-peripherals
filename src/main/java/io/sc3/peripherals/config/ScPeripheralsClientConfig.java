package io.sc3.peripherals.config;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import net.fabricmc.loader.api.FabricLoader;
import org.jetbrains.annotations.NotNull;


public final class ScPeripheralsClientConfig {
  @NotNull
  public static final ScPeripheralsClientConfig INSTANCE = new ScPeripheralsClientConfig();
  @NotNull
  private static final ConfigSpec spec = new ConfigSpec();

  private static final CommentedFileConfig config;
  @NotNull
  public static ConfigSpec getSpec() {
    return spec;
  }

  public static CommentedFileConfig getConfig() {
    return config;
  }

  static {
    spec.defineInRange("maxPosterRequestsPerTick",20, 1, 50);
    var dir = FabricLoader.getInstance().getConfigDir().resolve("luminacc");
    dir.toFile().mkdirs();
    var conf = CommentedFileConfig
      .builder(dir.resolve("lcc-peripherals-client.toml"))
      .autosave()
      .build();

    conf.load();
    spec.correct(conf);
    conf.save();
    config = conf;
  }
}
