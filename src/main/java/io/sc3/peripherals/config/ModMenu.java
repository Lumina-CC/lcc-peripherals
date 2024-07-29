package io.sc3.peripherals.config;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public final class ModMenu implements ModMenuApi {
  @NotNull
  public ConfigScreenFactory getModConfigScreenFactory() {
    return ModMenu::getModConfigScreenFactory;
  }

  private static CommentedFileConfig config = ScPeripheralsClientConfig.getConfig();
  private static void getModConfigScreenFactory1(Integer it) {
    ScPeripheralsClientConfig.getConfig().set("maxPosterRequestsPerTick", it);
  }
  private static void getModConfigScreenFactory2() {
    ScPeripheralsClientConfig.getSpec().correct((Config)ScPeripheralsClientConfig.getConfig());
    ScPeripheralsClientConfig.getConfig().save();
  }
  private static Screen getModConfigScreenFactory(Screen parent) {
    ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(Text.of("LuminaCC Peripherals")).setSavingRunnable(ModMenu::getModConfigScreenFactory2);
    ConfigCategory client = builder.getOrCreateCategory(Text.of("Client"));
    client.addEntry(builder.entryBuilder().startIntSlider(Text.of("Max Poster Requests Per Tick"), config.get("maxPosterRequestsPerTick"), 1, 50).setDefaultValue(20).setSaveConsumer(ModMenu::getModConfigScreenFactory1).build());
    return builder.build();
  }
}
