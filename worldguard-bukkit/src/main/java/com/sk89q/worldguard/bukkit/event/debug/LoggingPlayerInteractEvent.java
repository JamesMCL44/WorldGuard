/*
 * WorldGuard, a suite of tools for Minecraft
 * Copyright (C) sk89q <http://www.sk89q.com>
 * Copyright (C) WorldGuard team and contributors
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.sk89q.worldguard.bukkit.event.debug;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class LoggingPlayerInteractEvent extends PlayerInteractEvent implements CancelLogging {

    private final CancelLogger logger = new CancelLogger();

    public LoggingPlayerInteractEvent(Player who, Action action, ItemStack item, Block clickedBlock, BlockFace clickedFace) {
        super(who, action, item, clickedBlock, clickedFace);
    }

    @Override
    public List<CancelAttempt> getCancels() {
        return logger.getCancels();
    }

    @Override
    public void setUseInteractedBlock(Result useInteractedBlock) {
        this.logger.log(useInteractedBlock() == Result.DENY, useInteractedBlock == Result.DENY, new Exception().getStackTrace());
        super.setUseInteractedBlock(useInteractedBlock);
    }

    @Override
    public void setUseItemInHand(Result useItemInHand) {
        this.logger.log(useItemInHand() == Result.DENY, useItemInHand == Result.DENY, new Exception().getStackTrace());
        super.setUseItemInHand(useItemInHand);
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.logger.log(isCancelled(), cancel, new Exception().getStackTrace());
        super.setCancelled(cancel);
    }

}
