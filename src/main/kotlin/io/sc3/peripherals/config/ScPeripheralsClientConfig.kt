package io.sc3.peripherals.config

import com.electronwill.nightconfig.core.ConfigSpec
import com.electronwill.nightconfig.core.file.CommentedFileConfig
import net.fabricmc.loader.api.FabricLoader

object ScPeripheralsClientConfig {
  internal val spec = ConfigSpec()
  init {
    spec.defineInRange("maxPosterRequestsPerTick", 20, 1, 50)
  }

  internal val config by lazy {
    val dir = FabricLoader.getInstance().configDir.resolve("luminacc")
    dir.toFile().mkdirs()

    val conf = CommentedFileConfig
      .builder(dir.resolve("lcc-peripherals-client.toml"))
      .autosave()
      .build()

    conf.load()
    spec.correct(conf)
    conf.save()

    conf
  }
}
