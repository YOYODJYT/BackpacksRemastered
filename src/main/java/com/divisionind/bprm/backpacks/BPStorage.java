/*
 * BackpacksRemastered - remastered version of the popular Backpacks plugin
 * Copyright (C) 2019 Division Industries LLC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.divisionind.bprm.backpacks;

import com.divisionind.bprm.BackpackHandler;
import com.divisionind.bprm.BackpackSerialization;
import com.divisionind.bprm.NBTType;
import com.divisionind.bprm.PotentialBackpackItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public abstract class BPStorage implements BackpackHandler {

    private final String title;
    private final int size;

    BPStorage(String title, int size) {
        this.title = title;
        this.size = size;
    }

    @Override
    public Inventory openBackpack(Player p, PotentialBackpackItem backpack) throws Exception {
        if (backpack.hasData()) {
            return BackpackSerialization.fromByteArrayInventory(backpack.getData());
        } else {
            return Bukkit.getServer().createInventory(null, size, title);
        }
    }

    @Override
    public void onClose(InventoryCloseEvent e, PotentialBackpackItem backpack) throws Exception {
        backpack.setNBT(NBTType.BYTE_ARRAY, "backpack_data", BackpackSerialization.toByteArrayInventory(e.getInventory(), e.getView().getTitle()));
        e.getPlayer().getInventory().setChestplate(backpack.getModifiedItem());
    }
}