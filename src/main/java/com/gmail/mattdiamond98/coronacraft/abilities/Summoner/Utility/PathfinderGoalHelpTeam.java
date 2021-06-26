package com.gmail.mattdiamond98.coronacraft.abilities.Summoner.Utility;

import com.tommytony.war.Team;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityTargetEvent;


import java.util.EnumSet;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import net.minecraft.server.v1_16_R3.PathfinderGoal.Type;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

public class PathfinderGoalHelpTeam<T extends EntityLiving> extends PathfinderGoalTarget {
    protected final Class<T> a;
    protected final int b;
    protected EntityLiving c;
    protected PathfinderTargetCondition d;


    public PathfinderGoalHelpTeam(EntityInsentient entityinsentient, Class<T> oclass, boolean flag) {
        this(entityinsentient, oclass, flag, false);
    }

    public PathfinderGoalHelpTeam(EntityInsentient entityinsentient, Class<T> oclass, boolean flag, boolean flag1) {
        this(entityinsentient, oclass, 10, flag, flag1, (Predicate)null);
    }

    public PathfinderGoalHelpTeam(EntityInsentient entityinsentient, Class<T> oclass, int i, boolean flag, boolean flag1, @Nullable Predicate<EntityLiving> predicate) {
        super(entityinsentient, flag, flag1);

        Predicate<EntityLiving> predicatey=new Predicate<EntityLiving>() {
            @Override
            public boolean test(EntityLiving EL) {
                Team team=Team.getNonPlayerEntityTeam(entityinsentient.getBukkitEntity());
                if(team!=null){

              if(EL instanceof EntityPlayer) {
                  return !team.getPlayers().contains((Player)EL.getBukkitEntity());
              }else{return !team.getNonPlayerEntities().contains(EL.getBukkitEntity());}}

                return false;

            }
        };
         this.a = oclass;
        this.b = i;
        this.a(EnumSet.of(Type.TARGET));
        this.d = (new PathfinderTargetCondition()).a(this.k()).a(predicatey);


    }

    public boolean a() {
        if (this.b > 0 && this.e.getRandom().nextInt(this.b) != 0) {
            return false;
        } else {
            this.g();
            return this.c != null;
        }
    }

    protected AxisAlignedBB a(double d0) {
        return this.e.getBoundingBox().grow(d0, 4.0D, d0);
    }

    protected void g() {
        if (this.a != EntityHuman.class && this.a != EntityPlayer.class) {
            this.c = this.e.world.b(this.a, this.d, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ(), this.a(this.k()));
        } else {
            this.c = this.e.world.a(this.d, this.e, this.e.locX(), this.e.getHeadY(), this.e.locZ());
        }

    }

    public void c() {
        this.e.setGoalTarget(this.c, this.c instanceof EntityPlayer ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.CLOSEST_ENTITY, true);
        super.c();
    }
}
