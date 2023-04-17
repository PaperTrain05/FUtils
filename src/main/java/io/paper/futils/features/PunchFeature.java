package io.paper.futils.features;

import io.paper.futils.FUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

public class PunchFeature implements Listener {

    private final double speed2 = FUtils.punchfeature.getDouble("punch.speed2");
    private final double newvelocity = FUtils.punchfeature.getDouble("punch.newvelocity");


    @EventHandler
    public void OnShoot(EntityShootBowEvent entità) {
        if (FUtils.punchfeature.getBoolean("punch.enabled")) {
            LivingEntity entitàvivente = entità.getEntity();
            Vector vettore = entitàvivente.getLocation().getDirection();
            Arrow freccia = (Arrow)entità.getProjectile();
            double speed = freccia.getVelocity().length();
            Vector velo = vettore.multiply(speed);
            if (entità.getBow().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 1) {
                freccia.setVelocity(velo);
            }
            if (entità.getBow().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 2) {
                freccia.setVelocity(velo);
            }
            if (entità.getBow().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 3) {
                freccia.setVelocity(velo);
            }
        }
    }

    @EventHandler
    public void onVelocity(PlayerVelocityEvent evento) {
        if(FUtils.punchfeature.getBoolean("punch.enabled")) {
            Player p = evento.getPlayer();
            Vector vettore = evento.getVelocity();
            EntityDamageEvent aiah = p.getLastDamageCause();
            if (aiah != null && !aiah.isCancelled() && aiah instanceof EntityDamageByEntityEvent && ((EntityDamageByEntityEvent)aiah).getDamager() instanceof Arrow) {
                Vector VelocitàNuova;
                Vector direzione;
                double speed;
                Arrow arrow = (Arrow)((EntityDamageByEntityEvent)aiah).getDamager();
                if (arrow.getShooter().equals(p) && p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 1) {
                    speed = Math.sqrt(vettore.getX() * vettore.getX() + this.speed2 + vettore.getZ() * vettore.getZ() + this.speed2);
                    direzione = arrow.getLocation().getDirection().normalize();
                    VelocitàNuova = new Vector(direzione.getX() * speed * -this.newvelocity, vettore.getY(), direzione.getZ() * speed);
                    evento.setVelocity(VelocitàNuova);
                }
                if (arrow.getShooter().equals(p) && p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 2) {
                    speed = Math.sqrt(vettore.getX() * vettore.getX() + this.speed2 + vettore.getZ() * vettore.getZ() + this.speed2);
                    direzione = arrow.getLocation().getDirection().normalize();
                    VelocitàNuova = new Vector(direzione.getX() * speed * -this.newvelocity, vettore.getY(), direzione.getZ() * speed);
                    evento.setVelocity(VelocitàNuova);
                }
                if (arrow.getShooter().equals(p) && p.getItemInHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_KNOCKBACK) == 3) {
                    speed = Math.sqrt(vettore.getX() * vettore.getX() + this.speed2 + vettore.getZ() * vettore.getZ() + this.speed2);
                    direzione = arrow.getLocation().getDirection().normalize();
                    VelocitàNuova = new Vector(direzione.getX() * speed * -this.newvelocity, vettore.getY(), direzione.getZ() * speed);
                    evento.setVelocity(VelocitàNuova);
                }
            }
        }
    }

}
