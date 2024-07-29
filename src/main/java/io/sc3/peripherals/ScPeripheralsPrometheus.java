package io.sc3.peripherals;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.HTTPServer;
import io.sc3.peripherals.config.ScPeripheralsConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import kotlin.Unit;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ScPeripheralsPrometheus {
  @NotNull
  public static final ScPeripheralsPrometheus INSTANCE = new ScPeripheralsPrometheus();
  private static final Logger log = LoggerFactory.getLogger(ScPeripheralsPrometheus.class);
  @Nullable
  private static HTTPServer prometheusServer;
  @NotNull
  public static final CollectorRegistry registry = new CollectorRegistry(true);
  private static CommentedFileConfig config = ScPeripheralsConfig.getConfig();
  private ScPeripheralsPrometheus() {
  }

  @NotNull
  public  CollectorRegistry getRegistry() {
    return registry;
  }

  public static void init() {
    ServerLifecycleEvents.SERVER_STARTING.register(ScPeripheralsPrometheus::init);
    ServerLifecycleEvents.SERVER_STOPPING.register(ScPeripheralsPrometheus::init1);
  }

  private static void init(MinecraftServer it) {
    if (config.get("prometheus.enabled")) {
      log.info("Starting Prometheus server on port " + config.get("prometheus.port"));
      try {
        prometheusServer = new HTTPServer(new InetSocketAddress(config.get("prometheus.port")), registry, true);
      } catch (IOException ignored) {}
    }

  }

  private static void init1(MinecraftServer it) {
    try {
      log.info("Stopping Prometheus server");
        prometheusServer.close();
        prometheusServer = null;
    } catch (Exception var5) {
      log.error("Failed to stop Prometheus server", (Throwable)var5);
    }
  }
}
