package me.b1vth420.marsChoroby.Api;

import me.b1vth420.marsChoroby.Objects.Disease;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDiseaseChangeEvent extends Event implements Cancellable {

    private boolean isCancelled;
    private static final HandlerList handlers  = new HandlerList();
    private Player p;
    private Disease d;

    public PlayerDiseaseChangeEvent(Player p, Disease d) {
        this.p = p;
        this.isCancelled = false;
        this.d = d;
    }


    @Override
    public boolean isCancelled() {
        return this.isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.isCancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() { return handlers ; }

    public Player getPlayer() { return this.p; }

    public Disease getDisease() {
        return d;
    }
}
