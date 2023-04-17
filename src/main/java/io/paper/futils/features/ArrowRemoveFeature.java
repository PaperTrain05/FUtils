package io.paper.futils.features;

import io.paper.futils.FUtils;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowRemoveFeature implements Listener {

    @EventHandler
    public void ProjectileHit(ProjectileHitEvent e) {
        if(FUtils.punchfeature.getBoolean("punch.enabled")){
            Projectile entity = e.getEntity();
            if (e.getEntity() instanceof Arrow) {
                Arrow arrow = (Arrow)e.getEntity();
                arrow.remove();
            }
        }
    }

}
