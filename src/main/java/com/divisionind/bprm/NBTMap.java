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

package com.divisionind.bprm;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

public class NBTMap { // TODO rework nms reflector to use this for getting/setting nbt tags

    private Object tagCompound;

    public NBTMap(Object nbtBase) throws ClassCastException {
        this.tagCompound = NMSReflector.getInstance().cNBTTagCompound.cast(nbtBase);
    }

    public NBTMap() throws IllegalAccessException, InstantiationException {
        this.tagCompound = NMSReflector.getInstance().cNBTTagCompound.newInstance();
    }

    public Object getTagCompound() {
        return tagCompound;
    }

    public void setNBT(NBTType type, String key, Object value) throws InvocationTargetException, IllegalAccessException {
        NMSReflector.setNBT(tagCompound, type, key, value);
    }

    public Object getNBT(NBTType type, String key) throws InvocationTargetException, IllegalAccessException {
        return NMSReflector.getNBT(tagCompound, type, key);
    }

    public Set<String> getKeys() throws InvocationTargetException, IllegalAccessException {
        return NMSReflector.getKeys(tagCompound);
    }
}