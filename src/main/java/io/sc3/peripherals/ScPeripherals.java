package io.sc3.peripherals;

import io.sc3.peripherals.config.ScPeripheralsConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ScPeripherals implements ModInitializer {
  @NotNull
  public static final ScPeripherals INSTANCE = new ScPeripherals();
  @NotNull
  private static final Logger log;
  @NotNull
  public static final String modId = "lcc-peripherals";

  @NotNull
  public Logger getLog() {
    return log;
  }

  @NotNull
  public Identifier ModId(@NotNull String value) {
    return new Identifier(modId, value);
  }

  public void onInitialize() {
    log.info(modId+" initializing");
    ScPeripheralsConfig.getConfig().load();
    ScPeripheralsPrometheus.init();
    Registration.INSTANCE.init$lcc_peripherals();
  }

  static {
    log = LoggerFactory.getLogger(modId);
  }
}
