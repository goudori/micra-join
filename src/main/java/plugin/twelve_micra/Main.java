package plugin.twelve_micra;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

  @Override
  public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  // プレイヤーがマイクラサーバーに参加した時のイベント
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();
    // ベッドアイテム一個追加
    ItemStack bedItem = new ItemStack(Material.YELLOW_BED, 1);
    ItemStack iron = new ItemStack(Material.IRON_BLOCK, 1);
    player.getInventory().addItem(bedItem, iron);

    // スーパーマンに変身
    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    LeatherArmorMeta chestMeta = (LeatherArmorMeta) chestplate.getItemMeta();
    chestMeta.setColor(Color.BLUE);
    chestplate.setItemMeta(chestMeta);

    ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
    LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
    leggingsMeta.setColor(Color.BLUE);
    leggings.setItemMeta(leggingsMeta);

    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
    bootsMeta.setColor(Color.RED);
    boots.setItemMeta(bootsMeta);

    ItemStack elytra = new ItemStack(Material.ELYTRA);

    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

    player.getInventory().setChestplate(chestplate);
    player.getInventory().setLeggings(leggings);
    player.getInventory().setBoots(boots);
    player.getInventory().setItemInOffHand(sword);

    player.getInventory().setChestplate(elytra);

    // タイトルメッセージ
    String title = getRainbowText("中級編スタート");
    player.sendTitle(title, "", 10, 70, 20);



    // レインボー花火を出現させる
    spawnFirework(player.getLocation());
  }

  private String getRainbowText(String text) {
    ChatColor[] colors = {
        ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW,
        ChatColor.GREEN, ChatColor.AQUA, ChatColor.BLUE,
        ChatColor.LIGHT_PURPLE
    };
    StringBuilder coloredText = new StringBuilder();
    int colorIndex = 0;
    for (char c : text.toCharArray()) {
      coloredText.append(colors[colorIndex]).append(c);
      colorIndex = (colorIndex + 1) % colors.length;
    }
    return coloredText.toString();
  }

  private void spawnFirework(Location location) {
    Firework firework = location.getWorld().spawn(location, Firework.class);
    FireworkMeta meta = firework.getFireworkMeta();

    FireworkEffect effect = FireworkEffect.builder()
        .withColor(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.GRAY,
            Color.BLACK)
        .withFade(Color.WHITE)
        .with(Type.BALL_LARGE)
        .withFlicker()
        .withTrail()
        .build();

    meta.addEffect(effect);
    meta.setPower(1);

    firework.setFireworkMeta(meta);
  }
}
